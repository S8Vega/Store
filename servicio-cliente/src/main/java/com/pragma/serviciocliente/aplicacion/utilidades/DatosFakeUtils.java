package com.pragma.serviciocliente.aplicacion.utilidades;

import com.github.javafaker.Faker;
import com.pragma.serviciocliente.dominio.Cliente;
import com.pragma.serviciocliente.dominio.Foto;
import com.pragma.serviciocliente.dominio.TipoIdentifiacion;
import com.pragma.serviciocliente.infraestructura.persistencia.entidad.CiudadEntidad;
import com.pragma.serviciocliente.infraestructura.persistencia.entidad.ClienteEntidad;
import com.pragma.serviciocliente.infraestructura.persistencia.entidad.IdentificacionEntidad;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class DatosFakeUtils {

    private static Faker faker = new Faker(new Locale("es"));

    public static Cliente getCliente() {
        return Cliente.builder()
                .nombres(faker.name().firstName())
                .apellidos(faker.name().lastName())
                .tipoIdentificacion(TipoIdentifiacion.CEDULA)
                .numeroIdentificacion(Integer.toString(faker.number().numberBetween(1, 99999999)))
                .edad(faker.number().numberBetween(1, 100))
                .ciudadNacimiento(faker.address().city())
                .foto(faker.app().name())
                .build();
    }

    public static Cliente getCliente(ClienteEntidad clienteEntidad, Foto foto) {
        return Cliente.builder()
                .nombres(clienteEntidad.getNombres())
                .apellidos(clienteEntidad.getApellidos())
                .tipoIdentificacion(TipoIdentifiacion.valueOf(clienteEntidad.getIdentificacionEntidad().getTipo()))
                .numeroIdentificacion(clienteEntidad.getIdentificacionEntidad().getNumero())
                .edad(clienteEntidad.getEdad())
                .ciudadNacimiento(clienteEntidad.getCiudadDeNacimiento().getNombre())
                .foto(foto.getFoto())
                .build();
    }

    public static Foto getFoto(long clienteId) {
        return Foto.builder()
                .clienteId(clienteId)
                .foto(faker.app().name())
                .build();
    }

    public static IdentificacionEntidad getIdentificacionEntidad() {
        return IdentificacionEntidad.builder()
                .tipo(TipoIdentifiacion.CEDULA.toString())
                .numero(Integer.toString(faker.number().numberBetween(1, 99999999)))
                .build();
    }

    public static ClienteEntidad getClienteEntidad() {
        return ClienteEntidad.builder()
                .id(faker.number().numberBetween(1L, 100000000000000L))
                .nombres(faker.name().firstName())
                .apellidos(faker.name().lastName())
                .edad(faker.number().numberBetween(1, 100))
                .build();
    }

    public static CiudadEntidad geCiudadEntidad() {
        return CiudadEntidad.builder()
                .nombre(faker.address().city())
                .build();
    }
}
