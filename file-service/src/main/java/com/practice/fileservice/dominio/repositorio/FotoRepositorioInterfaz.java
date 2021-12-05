package com.practice.fileservice.dominio.repositorio;

import com.practice.fileservice.infraestructura.persistencia.entidad.FotoEntidad;

import java.util.Optional;

public interface FotoRepositorioInterfaz {
    Optional<FotoEntidad> buscarPorClienteId(Long clienteId);

    void guardar(FotoEntidad fotoEntidad);

    void eliminar(FotoEntidad fotoEntidad);
}
