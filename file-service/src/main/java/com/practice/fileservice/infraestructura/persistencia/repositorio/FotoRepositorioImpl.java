package com.practice.fileservice.infraestructura.persistencia.repositorio;

import com.practice.fileservice.dominio.repositorio.FotoRepositorioInterfaz;
import com.practice.fileservice.infraestructura.persistencia.dao.FotoDaoInterfaz;
import com.practice.fileservice.infraestructura.persistencia.entidad.FotoEntidad;
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
