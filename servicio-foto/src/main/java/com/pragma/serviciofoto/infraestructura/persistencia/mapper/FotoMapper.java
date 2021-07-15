package com.pragma.serviciofoto.infraestructura.persistencia.mapper;

import com.pragma.serviciofoto.dominio.Foto;
import com.pragma.serviciofoto.infraestructura.persistencia.entidad.FotoEntidad;
import com.pragma.serviciofoto.infraestructura.persistencia.repositorio.FotoRepositorioImpl;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FotoMapper {

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
