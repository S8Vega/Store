package com.pragma.serviciocliente.infraestructura.persistencia.repositorio;

import com.pragma.serviciocliente.dominio.repositorio.CiudadReposirorioInterfaz;
import com.pragma.serviciocliente.infraestructura.persistencia.dao.CiudadDaoInterfaz;
import com.pragma.serviciocliente.infraestructura.persistencia.entidad.CiudadEntidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CiudadRepositoryImpl implements CiudadReposirorioInterfaz {

    @Autowired
    private CiudadDaoInterfaz ciudadDao;

    @Override
    public Optional<CiudadEntidad> buscarPorNombre(String nombre) {
        return ciudadDao.findByNombre(nombre);
    }

    @Override
    public void guardar(CiudadEntidad ciudadEntidad) {
        ciudadDao.save(ciudadEntidad);
    }
}
