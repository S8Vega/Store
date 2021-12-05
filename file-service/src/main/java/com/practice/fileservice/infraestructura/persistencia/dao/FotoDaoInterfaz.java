package com.practice.fileservice.infraestructura.persistencia.dao;

import com.practice.fileservice.infraestructura.persistencia.entidad.FotoEntidad;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FotoDaoInterfaz extends MongoRepository<FotoEntidad, String> {
    Optional<FotoEntidad> findByClienteId(Long clienteId);
}
