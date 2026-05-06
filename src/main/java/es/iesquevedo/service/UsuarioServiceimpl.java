package es.iesquevedo.service;

import es.iesquevedo.modelo.Usuario;
import es.iesquevedo.dao.UsuarioRepository;

import java.util.List;
import java.util.Optional;

public class UsuarioServiceimpl implements UsuarioService {
    private final UsuarioRepository repo;
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceimpl(UsuarioRepository usuarioRepository) {
        this.repo = usuarioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public boolean altaUsuario(Usuario u) {
        if (u.getId() == null || u.getId().isBlank()) return false;
        if (u.getNombre() == null || u.getNombre().isBlank()) return false;
        return repo.create(u);
    }

    @Override
    public boolean bajaUsuario(String id) {
        return repo.deleteById(id);
    }

    @Override
    public List<Usuario> listar() {
        return repo.findAll();
    }

    @Override
    public Optional<Usuario> buscarPorId(String id) {
        return repo.findById(id);
    }
}
