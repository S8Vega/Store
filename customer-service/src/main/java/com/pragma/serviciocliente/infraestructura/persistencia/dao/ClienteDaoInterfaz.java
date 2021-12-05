package com.pragma.serviciocliente.infraestructura.persistencia.dao;

import com.pragma.serviciocliente.infraestructura.persistencia.entidad.ClienteEntidad;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteDaoInterfaz extends CrudRepository<ClienteEntidad, Long> {

    Optional<List<ClienteEntidad>> findByEdadGreaterThanEqual(Integer edad);
}
