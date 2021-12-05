package com.practice.fileservice.infraestructura.persistencia.mapper;

import com.practice.fileservice.dominio.Foto;
import com.practice.fileservice.infraestructura.persistencia.entidad.FotoEntidad;
import com.practice.fileservice.infraestructura.persistencia.repositorio.FotoRepositorioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FotoMapper {

    @Autowired
    private FotoRepositorioImpl fotoRepositorio;

    public Foto entidadADominio(FotoEntidad fotoEntidad) {
        return Foto.builder()
                .clienteId(fotoEntidad.getClienteId())
                .foto(fotoEntidad.getFoto())
                .build();
    }

    public FotoEntidad dominioAEntidad(Foto foto) {
        Optional<FotoEntidad> fotoEntidadOptional = fotoRepositorio.buscarPorClienteId(foto.getClienteId());
        if (fotoEntidadOptional.isEmpty()) {
            fotoEntidadOptional = Optional.of(FotoEntidad.builder().build());
        }
        fotoEntidadOptional.get().setFoto(foto.getFoto());
        fotoEntidadOptional.get().setClienteId(foto.getClienteId());
        return fotoEntidadOptional.get();
    }
}
