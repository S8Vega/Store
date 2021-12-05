package com.pragma.serviciocliente.dominio.repositorio;

import com.pragma.serviciocliente.infraestructura.persistencia.entidad.IdentificacionEntidad;

import java.util.Optional;

public interface IdentificacionRepositorioInterfaz {
    Optional<IdentificacionEntidad> buscarPorTipoYNumero(String tipo, String numero);

    void guardar(IdentificacionEntidad identificacionEntidad);

    void eliminar(IdentificacionEntidad identificacionEntidad);
}
