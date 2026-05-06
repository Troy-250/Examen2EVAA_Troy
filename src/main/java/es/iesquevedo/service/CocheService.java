package es.iesquevedo.service;

import es.iesquevedo.modelo.Coche;

import java.util.List;
import java.util.Optional;

public interface CocheService {
    boolean altaCoche(Coche coche);

    boolean bajaCoche(String id);

    List<Coche> listar();

    Optional<Coche> buscarPorId(String id);

    void actualizar(Coche coche);
}
