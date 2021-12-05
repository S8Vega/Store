package com.pragma.serviciocliente.dominio.servicio;

import com.pragma.serviciocliente.dominio.repositorio.CiudadReposirorioInterfaz;
import com.pragma.serviciocliente.infraestructura.persistencia.entidad.CiudadEntidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CiudadServicio {
    @Autowired
    private CiudadReposirorioInterfaz ciudadReposirorio;

    public CiudadEntidad guardar(String nombre) {
        Optional<CiudadEntidad> ciudadEntidad = ciudadReposirorio.buscarPorNombre(nombre);
        if (ciudadEntidad.isEmpty()) {
            ciudadEntidad = Optional.of(CiudadEntidad.builder().nombre(nombre).build());
            ciudadReposirorio.guardar(ciudadEntidad.get());
            ciudadEntidad = ciudadReposirorio.buscarPorNombre(nombre);
        }
        return ciudadEntidad.get();
    }
}
