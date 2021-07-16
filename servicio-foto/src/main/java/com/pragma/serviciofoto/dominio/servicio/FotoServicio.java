package com.pragma.serviciofoto.dominio.servicio;

import com.pragma.serviciofoto.aplicacion.utilidades.ErrorUtils;
import com.pragma.serviciofoto.dominio.Foto;
import com.pragma.serviciofoto.dominio.repositorio.FotoRepositorioInterfaz;
import com.pragma.serviciofoto.infraestructura.persistencia.entidad.FotoEntidad;
import com.pragma.serviciofoto.infraestructura.persistencia.mapper.FotoMapper;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FotoServicio {

    private final Logger log = LoggerFactory.getLogger(FotoServicio.class);
    @Autowired
    private FotoRepositorioInterfaz fotoRepositorio;
    @Autowired
    private FotoMapper fotoMapper;

    public Foto buscarPorClienteId(Long clienteId) throws NotFoundException {
        Optional<FotoEntidad> fotoEntidad = fotoRepositorio.buscarPorClienteId(clienteId);
        if (fotoEntidad.isEmpty()) {
            throw new NotFoundException(ErrorUtils.clienteSinFoto(clienteId));
        }
        return fotoMapper.entidadADominio(fotoEntidad.get());
    }

    public void guardar(Foto foto) {
        FotoEntidad fotoEntidad = fotoMapper.dominioAEntidad(foto);
        fotoRepositorio.guardar(fotoEntidad);
    }

    public void eliminar(Long clienteId) {
        Optional<FotoEntidad> fotoEntidad = fotoRepositorio.buscarPorClienteId(clienteId);
        fotoEntidad.ifPresent(entidad -> fotoRepositorio.eliminar(entidad));
    }

    public List<Foto> buscarPorClienteIds(List<Long> clientesIds) {
        List<Foto> fotos = new ArrayList<>();
        for (Long id : clientesIds) {
            try {
                Foto foto = buscarPorClienteId(id);
                fotos.add(foto);
            } catch (Exception e) {
                log.warn(e.getMessage());
            }
        }
        return fotos;
    }
}
