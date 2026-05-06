package es.iesquevedo.dao;

import es.iesquevedo.modelo.Alquiler;

import java.util.List;
import java.util.Optional;

public interface AlquilerRepository {
    void load();

    void save();

    boolean create(Alquiler alquiler);

    boolean deleteById(String id);

    List<Alquiler> findAll();

    Optional<Alquiler> findById(String id);
}
