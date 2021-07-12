package com.pragma.serviciocliente.infraestructura.persistencia.dao;

import com.pragma.serviciocliente.infraestructura.persistencia.entidad.CiudadEntidad;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CiudadDaoInterfaz extends CrudRepository<CiudadEntidad, Long> {
    Optional<CiudadEntidad> findByNombre(String nombre);
}
