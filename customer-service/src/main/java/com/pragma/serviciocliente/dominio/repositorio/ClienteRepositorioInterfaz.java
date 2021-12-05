package com.pragma.serviciocliente.dominio.repositorio;

import com.pragma.serviciocliente.infraestructura.persistencia.entidad.ClienteEntidad;

import java.util.List;
import java.util.Optional;

public interface ClienteRepositorioInterfaz {

    Optional<List<ClienteEntidad>> obtenerPorEdadMayorIgual(Integer edad);

    List<ClienteEntidad> obtenerTodo();

    void guardar(ClienteEntidad clienteEntidad);

    void eliminar(ClienteEntidad clienteEntidad);
}
