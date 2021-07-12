package com.pragma.serviciocliente.dominio.servicio;

import com.pragma.serviciocliente.aplicacion.utilidades.ErrorUtils;
import com.pragma.serviciocliente.aplicacion.utilidades.StringUtils;
import com.pragma.serviciocliente.dominio.Cliente;
import com.pragma.serviciocliente.dominio.Foto;
import com.pragma.serviciocliente.dominio.repositorio.ClienteRepositorioInterfaz;
import com.pragma.serviciocliente.infraestructura.persistencia.entidad.CiudadEntidad;
import com.pragma.serviciocliente.infraestructura.persistencia.entidad.ClienteEntidad;
import com.pragma.serviciocliente.infraestructura.persistencia.entidad.IdentificacionEntidad;
import com.pragma.serviciocliente.infraestructura.persistencia.mapper.ClienteMapperInterface;
import com.pragma.serviciocliente.infraestructura.rest.FotoRest;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClienteServicio {

    private final Logger log = LoggerFactory.getLogger(ClienteServicio.class);
    @Autowired
    private ClienteRepositorioInterfaz clienteRepositorio;
    @Autowired
    private CiudadServicio ciudadServicio;
    @Autowired
    private IdentificacionServicio identificacionServicio;
    @Autowired
    private ClienteMapperInterface clienteMapper;
    @Autowired
    private FotoRest fotoRest;

    public Cliente obtenetPorIdentificacion(String tipoIdentificacion, String numeroIdentificacion) throws Exception {
        IdentificacionEntidad identificacionEntidad = identificacionServicio.obtenerPorTipoYNumero(
                tipoIdentificacion,
                numeroIdentificacion
        );
        ClienteEntidad clienteEntidad = identificacionEntidad.getClienteEntidad();
        return getCliente(clienteEntidad);
    }

    public List<Cliente> obtenerPorEdadMayorIgual(Integer edad) throws Exception {
        Optional<List<ClienteEntidad>> clientesEntidad = clienteRepositorio.obtenerPorEdadMayorIgual(edad);
        if (clientesEntidad.isEmpty()) {
            throw new NotFoundException(ErrorUtils.sinClientesPorEdad(edad));
        }
        return getClientes(clientesEntidad.get());
    }

    public List<Cliente> obtenerTodos() throws Exception {
        List<ClienteEntidad> clientesEntidad = clienteRepositorio.obtenerTodo();
        return getClientes(clientesEntidad);
    }

    public void guardar(Cliente cliente) throws Exception {
        if (identificacionServicio.identificaionExiste(cliente.getTipoIdentificacion().toString(), cliente.getNumeroIdentificacion())) {
            throw new IllegalArgumentException(
                    ErrorUtils.identificacionYaRegistrada(
                            cliente.getTipoIdentificacion().toString(),
                            cliente.getNumeroIdentificacion())
            );
        }
        CiudadEntidad ciudadEntidad = ciudadServicio.guardar(cliente.getCiudadNacimiento());
        ClienteEntidad clienteEntidad = ClienteEntidad.builder()
                .ciudadDeNacimiento(ciudadEntidad)
                .apellidos(cliente.getApellidos())
                .edad(cliente.getEdad())
                .nombres(cliente.getNombres())
                .build();
        clienteRepositorio.guardar(clienteEntidad);
        identificacionServicio.guardar(
                clienteEntidad,
                cliente.getTipoIdentificacion().toString(),
                cliente.getNumeroIdentificacion()
        );
        fotoRest.guardar(
                Foto.builder()
                        .clienteId(clienteEntidad.getId())
                        .foto(cliente.getFoto())
                        .build()
        );
    }

    public void actualizar(Cliente cliente) throws Exception {
        if (!identificacionServicio.identificaionExiste(cliente.getTipoIdentificacion().toString(), cliente.getNumeroIdentificacion())) {
            throw new NotFoundException(
                    ErrorUtils.identificacionNoRegistrada(
                            cliente.getTipoIdentificacion().toString(),
                            cliente.getNumeroIdentificacion()));
        }
        CiudadEntidad ciudadEntidad = ciudadServicio.guardar(cliente.getCiudadNacimiento());
        IdentificacionEntidad identificacionEntidad = identificacionServicio.obtenerPorTipoYNumero(
                cliente.getTipoIdentificacion().toString(),
                cliente.getNumeroIdentificacion()
        );
        ClienteEntidad clienteEntidad = identificacionEntidad.getClienteEntidad();
        clienteEntidad.setCiudadDeNacimiento(ciudadEntidad);
        clienteEntidad.setApellidos(cliente.getApellidos());
        clienteEntidad.setEdad(cliente.getEdad());
        clienteEntidad.setNombres(cliente.getNombres());
        clienteRepositorio.guardar(clienteEntidad);
        fotoRest.guardar(
                Foto.builder()
                        .clienteId(clienteEntidad.getId())
                        .foto(cliente.getFoto())
                        .build()
        );
    }

    public void eliminar(String tipoIdentificacion, String numeroIdentificacion) throws Exception {
        IdentificacionEntidad identificacionEntidad = identificacionServicio.obtenerPorTipoYNumero(
                tipoIdentificacion,
                numeroIdentificacion
        );
        ClienteEntidad clienteEntidad = identificacionEntidad.getClienteEntidad();
        identificacionServicio.borrar(identificacionEntidad);
        fotoRest.eliminar(clienteEntidad.getId());
        clienteRepositorio.eliminar(clienteEntidad);
    }

    private List<Cliente> getClientes(List<ClienteEntidad> clientesEntidad) {
        List<Cliente> clientes = new ArrayList<>();
        ResponseEntity<List<Foto>> fotoResponseEntity = fotoRest.obtenerPorClienteIds(
                clientesEntidad
                        .stream()
                        .map(ClienteEntidad::getId)
                        .collect(Collectors.toList())
        );
        Map<Long, String> fotos = new HashMap<>();
        if (fotoResponseEntity.getStatusCode().is2xxSuccessful()) {
            fotos = fotoResponseEntity.getBody()
                    .stream()
                    .collect(Collectors.toMap(Foto::getClienteId, Foto::getFoto));
        }
        Foto foto = Foto.builder().build();
        for (ClienteEntidad clienteEntidad : clientesEntidad) {
            foto.setFoto(fotos.getOrDefault(clienteEntidad.getId(), StringUtils.NO_HAY_FOTO));
            clientes.add(clienteMapper.aCliente(clienteEntidad, foto));
        }
        return clientes;
    }

    private Cliente getCliente(ClienteEntidad clienteEntidad) {
        ResponseEntity<Foto> fotoResponseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        try {
            fotoResponseEntity = fotoRest.obtenerPorClienteId(clienteEntidad.getId());
        } catch (Exception exception) {
            log.error(exception.toString());
        }
        Foto foto;
        if (fotoResponseEntity.getStatusCode().equals(HttpStatus.OK)) {
            foto = fotoResponseEntity.getBody();
        } else {
            foto = Foto.builder().foto(StringUtils.NO_HAY_FOTO).build();
        }
        return clienteMapper.aCliente(clienteEntidad, foto);
    }
}
