package com.pragma.serviciocliente.dominio.servicio;

import com.pragma.serviciocliente.aplicacion.utilidades.ErrorUtils;
import com.pragma.serviciocliente.aplicacion.utilidades.StringUtils;
import com.pragma.serviciocliente.dominio.repositorio.IdentificacionRepositorioInterfaz;
import com.pragma.serviciocliente.infraestructura.persistencia.entidad.ClienteEntidad;
import com.pragma.serviciocliente.infraestructura.persistencia.entidad.IdentificacionEntidad;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IdentificacionServicio {

    @Autowired
    private IdentificacionRepositorioInterfaz identificacionRepositorio;

    public IdentificacionEntidad obtenerPorTipoYNumero(String tipo, String numero) throws Exception {
        if (StringUtils.contieneLetras(numero)) {
            throw new IllegalArgumentException(ErrorUtils.identificacionContieneLetras(tipo, numero));
        }
        Optional<IdentificacionEntidad> identificacionEntidad = identificacionRepositorio.buscarPorTipoYNumero(
                tipo,
                numero
        );
        if (identificacionEntidad.isEmpty()) {
            throw new NotFoundException(ErrorUtils.identificacionNoRegistrada(tipo, numero));
        }
        return identificacionEntidad.get();
    }

    public IdentificacionEntidad guardar(ClienteEntidad clienteEntidad, String tipo, String numero) {
        if (StringUtils.contieneLetras(numero)) {
            throw new IllegalArgumentException(ErrorUtils.identificacionContieneLetras(tipo, numero));
        }
        if (identificaionExiste(tipo, numero)) {
            throw new IllegalArgumentException(ErrorUtils.identificacionYaRegistrada(tipo, numero));
        }
        IdentificacionEntidad identificacionEntidad = IdentificacionEntidad.builder()
                .tipo(tipo)
                .numero(numero)
                .clienteEntidad(clienteEntidad)
                .build();
        identificacionRepositorio.guardar(identificacionEntidad);
        return identificacionEntidad;
    }

    public void borrar(IdentificacionEntidad identificacionEntidad) {
        identificacionRepositorio.eliminar(identificacionEntidad);
    }

    public boolean identificaionExiste(String tipo, String numero) {
        Optional<IdentificacionEntidad> identificacionEntidad = identificacionRepositorio.buscarPorTipoYNumero(tipo, numero);
        return identificacionEntidad.isPresent();
    }
}
