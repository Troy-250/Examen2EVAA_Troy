package es.iesquevedo.dao;

import com.google.gson.reflect.TypeToken;
import es.iesquevedo.modelo.Alquiler;
import es.iesquevedo.util.GsonFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AlquilerRepositoryimpl implements AlquilerRepository {
    private final Path file;
    private final Type listType = new TypeToken<List<Alquiler>>(){}.getType();
    private List<Alquiler> alquileres = new ArrayList<>();

    public AlquilerRepositoryimpl() {
        this.file  = Path.of("data", "alquileres.json");
        load();
    }

    @Override
    public void load() {
        try {
            if (Files.notExists(file.getParent())) Files.createDirectories(file.getParent());
            if (Files.notExists(file)) Files.writeString(file, "[]");
            String json = Files.readString(file);
            List<Alquiler> list = GsonFactory.getGson().fromJson(json, listType);
            if (list != null) alquileres = list;
        } catch (IOException e) {
            System.err.println("Error cargando alquileres: " + e.getMessage());
        }
    }

    @Override
    public void save() {
        try {
            Files.writeString(file, GsonFactory.getGson().toJson(alquileres, listType));
        } catch (IOException e) {
            System.err.println("Error guardando alquileres: " + e.getMessage());
        }
    }

    @Override
    public boolean create(Alquiler alquiler) {
        load();
        if (findById(alquiler.getId()).isPresent()) return false;
        alquileres.add(alquiler);
        save();
        return true;
    }

    @Override
    public boolean deleteById(String id) {
        load();
        boolean removed = alquileres.removeIf(a -> a.getId().equals(id));
        if (removed) save();
        return removed;
    }

    @Override
    public List<Alquiler> findAll() {
        load();
        return alquileres.stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Alquiler> findById(String id) {
        load();
        return alquileres.stream().filter(a -> a.getId().equals(id)).findFirst();
    }
}
