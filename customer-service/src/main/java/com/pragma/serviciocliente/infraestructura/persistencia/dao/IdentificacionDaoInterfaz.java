package com.pragma.serviciocliente.infraestructura.persistencia.dao;

import com.pragma.serviciocliente.infraestructura.persistencia.entidad.IdentificacionEntidad;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IdentificacionDaoInterfaz extends CrudRepository<IdentificacionEntidad, Long> {
    Optional<IdentificacionEntidad> findByTipoAndNumero(String tipo, String numero);
}
