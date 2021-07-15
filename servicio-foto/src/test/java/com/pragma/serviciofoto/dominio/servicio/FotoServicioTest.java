package com.pragma.serviciofoto.dominio.servicio;

import com.pragma.serviciofoto.aplicacion.utilidades.DatosFakeUtils;
import com.pragma.serviciofoto.dominio.Foto;
import com.pragma.serviciofoto.dominio.repositorio.FotoRepositorioInterfaz;
import com.pragma.serviciofoto.infraestructura.persistencia.entidad.FotoEntidad;
import com.pragma.serviciofoto.infraestructura.persistencia.mapper.FotoMapper;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FotoServicioTest {

    @Mock
    FotoRepositorioInterfaz fotoRepositorio;
    @Mock
    FotoMapper mapper;
    @InjectMocks
    FotoServicio fotoServicio;

    @Test
    void buscarPorClienteId() throws Exception {
        Foto foto = DatosFakeUtils.getFoto();
        FotoEntidad fotoEntidad = DatosFakeUtils.getFotoEntidad(foto.getClienteId(), foto.getFoto());
        when(
                fotoRepositorio.buscarPorClienteId(foto.getClienteId())
        ).thenReturn(Optional.of(fotoEntidad));
        when(
                mapper.entidadADominio(fotoEntidad)
        ).thenReturn(foto);
        assertEquals(foto, fotoServicio.buscarPorClienteId(foto.getClienteId()));
    }

    @Test
    void buscarPorClienteId_exception() {
        Foto foto = DatosFakeUtils.getFoto();
        when(
                fotoRepositorio.buscarPorClienteId(foto.getClienteId())
        ).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> fotoServicio.buscarPorClienteId(foto.getClienteId()));
    }
}