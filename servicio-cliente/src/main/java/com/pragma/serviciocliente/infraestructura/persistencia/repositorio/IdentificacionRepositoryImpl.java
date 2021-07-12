package com.pragma.serviciocliente.infraestructura.persistencia.repositorio;

import com.pragma.serviciocliente.dominio.repositorio.IdentificacionRepositorioInterfaz;
import com.pragma.serviciocliente.infraestructura.persistencia.dao.IdentificacionDaoInterfaz;
import com.pragma.serviciocliente.infraestructura.persistencia.entidad.IdentificacionEntidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class IdentificacionRepositoryImpl implements IdentificacionRepositorioInterfaz {

    @Autowired
    private IdentificacionDaoInterfaz identificacionDao;

    @Override
    public Optional<IdentificacionEntidad> buscarPorTipoYNumero(String tipo, String numero) {
        return identificacionDao.findByTipoAndNumero(tipo, numero);
    }

    @Override
    public void guardar(IdentificacionEntidad identificacionEntidad) {
        identificacionDao.save(identificacionEntidad);
    }

    @Override
    public void eliminar(IdentificacionEntidad identificacionEntidad) {
        identificacionDao.delete(identificacionEntidad);
    }
}
