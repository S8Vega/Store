package com.pragma.serviciofoto.dominio.repositorio;

import com.pragma.serviciofoto.infraestructura.persistencia.entidad.FotoEntidad;

import java.util.Optional;

public interface FotoRepositorioInterfaz {
    Optional<FotoEntidad> buscarPorClienteId(Long clienteId);

    void guardar(FotoEntidad fotoEntidad);

    void eliminar(FotoEntidad fotoEntidad);
}
