package com.pragma.serviciofoto.infraestructura.persistencia.dao;

import com.pragma.serviciofoto.infraestructura.persistencia.entidad.FotoEntidad;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FotoDaoInterfaz extends MongoRepository<FotoEntidad, String> {
    Optional<FotoEntidad> findByClienteId(Long clienteId);
}
