package es.iesquevedo.dao;

import es.iesquevedo.modelo.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {
    void load();

    void save();

    boolean create(Usuario usuario);

    boolean deleteById(String id);

    List<Usuario> findAll();

    Optional<Usuario> findById(String id);
}
