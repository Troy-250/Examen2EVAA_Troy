package es.iesquevedo.dao;

import es.iesquevedo.modelo.Coche;

import java.util.List;
import java.util.Optional;

public interface CocheRepository {
    void load();

    void save();

    boolean create(Coche coche);

    boolean deleteById(String id);

    List<Coche> findAll();

    Optional<Coche> findById(String id);

    void update(Coche updated);
}
