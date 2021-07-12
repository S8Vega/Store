package com.pragma.serviciofoto.infraestructura.persistencia.repositorio;

import com.pragma.serviciofoto.dominio.repositorio.FotoRepositorioInterfaz;
import com.pragma.serviciofoto.infraestructura.persistencia.dao.FotoDaoInterfaz;
import com.pragma.serviciofoto.infraestructura.persistencia.entidad.FotoEntidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class FotoRepositorioImpl implements FotoRepositorioInterfaz {

    @Autowired
    private FotoDaoInterfaz fotoDao;

    @Override
    public Optional<FotoEntidad> buscarPorClienteId(Long clienteId) {
        return fotoDao.findByClienteId(clienteId);
    }

    @Override
    public void guardar(FotoEntidad fotoEntidad) {
        fotoDao.save(fotoEntidad);
    }

    @Override
    public void eliminar(FotoEntidad fotoEntidad) {
        fotoDao.delete(fotoEntidad);
    }
}
