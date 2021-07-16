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
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FotoServicioTestUnitario {

    @Mock
    FotoRepositorioInterfaz fotoRepositorio;
    @Spy
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
        assertEquals(foto, fotoServicio.buscarPorClienteId(foto.getClienteId()));
        verify(fotoRepositorio).buscarPorClienteId(foto.getClienteId());
    }

    @Test
    void buscarPorClienteId_NotFoundException() {
        Foto foto = DatosFakeUtils.getFoto();
        when(
                fotoRepositorio.buscarPorClienteId(foto.getClienteId())
        ).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> fotoServicio.buscarPorClienteId(foto.getClienteId()));
        verify(fotoRepositorio).buscarPorClienteId(foto.getClienteId());
    }
}