package com.pragma.serviciocliente.dominio.servicio;

import com.pragma.serviciocliente.aplicacion.utilidades.DatosFakeUtils;
import com.pragma.serviciocliente.dominio.Cliente;
import com.pragma.serviciocliente.dominio.Foto;
import com.pragma.serviciocliente.dominio.repositorio.CiudadReposirorioInterfaz;
import com.pragma.serviciocliente.dominio.repositorio.ClienteRepositorioInterfaz;
import com.pragma.serviciocliente.dominio.repositorio.IdentificacionRepositorioInterfaz;
import com.pragma.serviciocliente.infraestructura.persistencia.entidad.CiudadEntidad;
import com.pragma.serviciocliente.infraestructura.persistencia.entidad.ClienteEntidad;
import com.pragma.serviciocliente.infraestructura.persistencia.entidad.IdentificacionEntidad;
import com.pragma.serviciocliente.infraestructura.persistencia.mapper.ClienteMapperInterface;
import com.pragma.serviciocliente.infraestructura.rest.FotoRest;
import javassist.NotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClienteServicioTest {
    private final Logger log = LoggerFactory.getLogger(ClienteServicioTest.class);
    @MockBean
    CiudadReposirorioInterfaz ciudadReposirorio;
    @MockBean
    ClienteRepositorioInterfaz clienteRepositorio;
    @MockBean
    IdentificacionRepositorioInterfaz identificacionRepositorio;
    @MockBean
    FotoRest fotoRest;
    @Autowired
    ClienteServicio clienteServicio;
    // objetos adicionales
    IdentificacionEntidad identificacionEntidad;
    CiudadEntidad ciudadEntidad;
    ClienteEntidad clienteEntidad;
    Foto foto;
    @Autowired
    private ClienteMapperInterface clienteMapper;

    @BeforeEach
    void setUp() throws Exception {
        log.info("setUp()");
        identificacionEntidad = DatosFakeUtils.getIdentificacionEntidad();
        clienteEntidad = DatosFakeUtils.getClienteEntidad();
        identificacionEntidad.setClienteEntidad(clienteEntidad);
        clienteEntidad.setIdentificacionEntidad(identificacionEntidad);
        ciudadEntidad = DatosFakeUtils.geCiudadEntidad();
        ciudadEntidad.setClientesEntidad(List.of(clienteEntidad));
        clienteEntidad.setCiudadDeNacimiento(ciudadEntidad);
        foto = DatosFakeUtils.getFoto(clienteEntidad.getId());
        ResponseEntity<Foto> fotoResponseEntity = new ResponseEntity<>(foto, HttpStatus.OK);
        when(
                identificacionRepositorio.buscarPorTipoYNumero(
                        clienteEntidad.getIdentificacionEntidad().getTipo(),
                        clienteEntidad.getIdentificacionEntidad().getNumero()
                )
        ).thenReturn(Optional.of(clienteEntidad.getIdentificacionEntidad()));
        when(
                fotoRest.obtenerPorClienteId(clienteEntidad.getId())
        ).thenReturn(fotoResponseEntity);
    }

    @AfterEach
    void tearDown() {
        log.info("tearDown()");
    }

    @Test
    void obtenetPorIdentificacion() throws Exception {
        log.info("obtenetPorIdentificacion()");
        Cliente clienteEsperado = clienteMapper.aCliente(clienteEntidad, foto);
        Cliente clienteActual = clienteServicio.obtenetPorIdentificacion(
                identificacionEntidad.getTipo(),
                identificacionEntidad.getNumero()
        );
        Assertions.assertEquals(clienteEsperado, clienteActual);
    }

    @Test
    void obtenetPorIdentificacion_notFound() {
        log.info("obtenetPorIdentificacion_notFound()");
        assertThrows(NotFoundException.class, () -> clienteServicio.obtenetPorIdentificacion(
                identificacionEntidad.getTipo(),
                "0"
        ));
    }

    @Test
    void obtenetPorIdentificacion_IllegalArgument() {
        log.info("obtenetPorIdentificacion_IllegalArgument()");
        assertThrows(IllegalArgumentException.class, () -> clienteServicio.obtenetPorIdentificacion(
                "CEDULA",
                "A0"
        ));
    }
}