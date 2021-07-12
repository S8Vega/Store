package com.pragma.serviciocliente.dominio.repositorio;

import com.pragma.serviciocliente.infraestructura.persistencia.entidad.CiudadEntidad;

import java.util.Optional;

public interface CiudadReposirorioInterfaz {
    Optional<CiudadEntidad> buscarPorNombre(String nombre);

    void guardar(CiudadEntidad ciudadEntidad);
}
