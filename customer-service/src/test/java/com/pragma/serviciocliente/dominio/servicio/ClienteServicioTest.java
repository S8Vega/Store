package com.pragma.serviciocliente.dominio.servicio;

import com.pragma.serviciocliente.aplicacion.utilidades.DatosFakeUtils;
import com.pragma.serviciocliente.dominio.Cliente;
import com.pragma.serviciocliente.dominio.Foto;
import com.pragma.serviciocliente.dominio.repositorio.ClienteRepositorioInterfaz;
import com.pragma.serviciocliente.infraestructura.persistencia.entidad.CiudadEntidad;
import com.pragma.serviciocliente.infraestructura.persistencia.entidad.ClienteEntidad;
import com.pragma.serviciocliente.infraestructura.persistencia.entidad.IdentificacionEntidad;
import com.pragma.serviciocliente.infraestructura.persistencia.mapper.ClienteMapperInterface;
import com.pragma.serviciocliente.infraestructura.rest.FotoRest;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServicioTest {

    @InjectMocks
    ClienteServicio clienteServicio;
    @Mock
    ClienteRepositorioInterfaz clienteRepositorio;
    @Mock
    IdentificacionServicio identificacionServicio;
    @Mock
    ClienteMapperInterface clienteMapper;
    @Mock
    FotoRest fotoRest;
    // objetos adicionales
    Cliente cliente;
    IdentificacionEntidad identificacionEntidad;
    CiudadEntidad ciudadEntidad;
    ClienteEntidad clienteEntidad;
    Foto foto;

    @BeforeEach
    void setUp() throws Exception {
        identificacionEntidad = DatosFakeUtils.getIdentificacionEntidad();
        clienteEntidad = DatosFakeUtils.getClienteEntidad();
        identificacionEntidad.setClienteEntidad(clienteEntidad);
        clienteEntidad.setIdentificacionEntidad(identificacionEntidad);
        ciudadEntidad = DatosFakeUtils.geCiudadEntidad();
        ciudadEntidad.setClientesEntidad(List.of(clienteEntidad));
        clienteEntidad.setCiudadDeNacimiento(ciudadEntidad);
        foto = DatosFakeUtils.getFoto(clienteEntidad.getId());
        cliente = DatosFakeUtils.getCliente(clienteEntidad, foto);
    }

    @Test
    void obtenetPorIdentificacion() throws Exception {
        when(
                identificacionServicio.obtenerPorTipoYNumero(
                        clienteEntidad.getIdentificacionEntidad().getTipo(),
                        clienteEntidad.getIdentificacionEntidad().getNumero()
                )
        ).thenReturn(identificacionEntidad);
        when(
                clienteMapper.aCliente(clienteEntidad, foto)
        ).thenReturn(cliente);
        ResponseEntity<Foto> fotoResponseEntity = new ResponseEntity<>(foto, HttpStatus.OK);
        when(
                fotoRest.obtenerPorClienteId(clienteEntidad.getId())
        ).thenReturn(fotoResponseEntity);

        Cliente clienteActual = clienteServicio.obtenerPorIdentificacion(
                identificacionEntidad.getTipo(),
                identificacionEntidad.getNumero()
        );

        assertEquals(cliente, clienteActual);
        verify(identificacionServicio).obtenerPorTipoYNumero(
                clienteEntidad.getIdentificacionEntidad().getTipo(),
                clienteEntidad.getIdentificacionEntidad().getNumero()
        );
        verify(clienteMapper).aCliente(clienteEntidad, foto);
        verify(fotoRest).obtenerPorClienteId(clienteEntidad.getId());
    }

    @Test
    void obtenetPorIdentificacion_notFound() throws Exception {
        when(
                identificacionServicio.obtenerPorTipoYNumero(
                        clienteEntidad.getIdentificacionEntidad().getTipo(),
                        clienteEntidad.getIdentificacionEntidad().getNumero()
                )
        ).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> clienteServicio.obtenerPorIdentificacion(
                identificacionEntidad.getTipo(),
                identificacionEntidad.getNumero()
        ));
    }

    @Test
    void obtenetPorIdentificacion_IllegalArgument() throws Exception {
        when(
                identificacionServicio.obtenerPorTipoYNumero(
                        clienteEntidad.getIdentificacionEntidad().getTipo(),
                        clienteEntidad.getIdentificacionEntidad().getNumero() + "a"
                )
        ).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> clienteServicio.obtenerPorIdentificacion(
                clienteEntidad.getIdentificacionEntidad().getTipo(),
                clienteEntidad.getIdentificacionEntidad().getNumero() + "a"
        ));
    }
}