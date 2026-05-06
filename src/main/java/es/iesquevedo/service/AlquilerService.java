package es.iesquevedo.service;

import es.iesquevedo.modelo.Alquiler;

import java.util.List;
import java.util.Optional;

public interface AlquilerService {
    boolean crearAlquiler(Alquiler a);

    boolean finalizarAlquiler(String id);

    List<Alquiler> listar();

    Optional<Alquiler> buscarPorId(String id);
}
