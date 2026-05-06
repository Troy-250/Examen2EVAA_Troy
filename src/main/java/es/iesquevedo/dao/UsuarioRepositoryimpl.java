package es.iesquevedo.dao;

import com.google.gson.reflect.TypeToken;
import es.iesquevedo.modelo.Usuario;
import es.iesquevedo.util.GsonFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UsuarioRepositoryimpl implements UsuarioRepository {
    private final Path file;
    private final Type listType = new TypeToken<List<Usuario>>(){}.getType();
    private List<Usuario> usuarios = new ArrayList<>();

    public UsuarioRepositoryimpl() {
        this.file = Path.of("data", "usuarios.json");
        load();
    }

    @Override
    public void load() {
        try {
            if (Files.notExists(file.getParent())) Files.createDirectories(file.getParent());
            if (Files.notExists(file)) Files.writeString(file, "[]");
            String json = Files.readString(file);
            List<Usuario> list = GsonFactory.getGson().fromJson(json, listType);
            if (list != null) usuarios = list;
        } catch (IOException e) {
            System.err.println("Error cargando usuarios: " + e.getMessage());
        }
    }

    @Override
    public void save() {
        try {
            Files.writeString(file, GsonFactory.getGson().toJson(usuarios, listType));
        } catch (IOException e) {
            System.err.println("Error guardando usuarios: " + e.getMessage());
        }
    }

    @Override
    public boolean create(Usuario usuario) {
        load();
        if (findById(usuario.getId()).isPresent()) return false;
        usuarios.add(usuario);
        save();
        return true;
    }

    @Override
    public boolean deleteById(String id) {
        load();
        boolean removed = usuarios.removeIf(u -> u.getId().equals(id));
        if (removed) save();
        return removed;
    }

    @Override
    public List<Usuario> findAll() {
        load();
        return usuarios.stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Usuario> findById(String id) {
        load();
        return usuarios.stream().filter(u -> u.getId().equals(id)).findFirst();
    }
}
