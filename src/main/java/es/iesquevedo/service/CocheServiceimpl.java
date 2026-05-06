package es.iesquevedo.service;

import es.iesquevedo.modelo.Coche;
import es.iesquevedo.dao.CocheRepository;

import java.util.List;
import java.util.Optional;

public class CocheServiceimpl implements CocheService {
    private final CocheRepository repo;

    public CocheServiceimpl() {
        this.repo = cocheRepository;
    }

    @Override
    public boolean altaCoche(Coche coche) {
        if (coche.getId() == null || coche.getId().isBlank()) return false;
        if (coche.getMatricula() == null || coche.getMatricula().isBlank()) return false;
        return repo.create(coche);
    }

    @Override
    public boolean bajaCoche(String id) {
        return repo.deleteById(id);
    }

    @Override
    public List<Coche> listar() {
        return repo.findAll();
    }

    @Override
    public Optional<Coche> buscarPorId(String id) {
        return repo.findById(id);
    }

    @Override
    public void actualizar(Coche coche) {
        repo.update(coche);
    }
}
