package es.iesquevedo.dao;

import com.google.gson.reflect.TypeToken;
import es.iesquevedo.modelo.Coche;
import es.iesquevedo.util.GsonFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CocheRepositoryimpl implements CocheRepository {
    private final Path file;
    private final Type listType = new TypeToken<List<Coche>>(){}.getType();
    private List<Coche> coches = new ArrayList<>();

    public CocheRepositoryimpl() {
        this.file = Path.of("data", "coches.json");
        load();
    }

    @Override
    public void load() {
        try {
            if (Files.notExists(file.getParent())) {
                Files.createDirectories(file.getParent());
            }
            if (Files.notExists(file)) {
                Files.writeString(file, "[]");
            }
            String json = Files.readString(file);
            List<Coche> list = GsonFactory.getGson().fromJson(json, listType);
            if (list != null) coches = list;
        } catch (IOException e) {
            System.err.println("Error cargando coches: " + e.getMessage());
        }
    }

    @Override
    public void save() {
        try {
            Files.writeString(file, GsonFactory.getGson().toJson(coches, listType));
        } catch (IOException e) {
            System.err.println("Error guardando coches: " + e.getMessage());
        }
    }

    @Override
    public boolean create(Coche coche) {
        // recargar estado antes de modificar
        load();
        if (findById(coche.getId()).isPresent()) return false;
        coches.add(coche);
        save();
        return true;
    }

    @Override
    public boolean deleteById(String id) {
        // recargar
        load();
        boolean removed = coches.removeIf(c -> c.getId().equals(id));
        if (removed) save();
        return removed;
    }

    @Override
    public List<Coche> findAll() {
        load();
        return coches.stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Coche> findById(String id) {
        load();
        return coches.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    @Override
    public void update(Coche updated) {
        load();
        for (int i = 0; i < coches.size(); i++) {
            if (coches.get(i).getId().equals(updated.getId())) {
                coches.set(i, updated);
                save();
                return;
            }
        }
    }
}
