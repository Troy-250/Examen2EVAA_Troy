package es.iesquevedo.service;

import es.iesquevedo.modelo.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    boolean altaUsuario(Usuario u);

    boolean bajaUsuario(String id);

    List<Usuario> listar();

    Optional<Usuario> buscarPorId(String id);
}
