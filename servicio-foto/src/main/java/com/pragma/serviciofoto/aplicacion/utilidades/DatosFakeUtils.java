package com.pragma.serviciofoto.aplicacion.utilidades;

import com.github.javafaker.Faker;
import com.pragma.serviciofoto.dominio.Foto;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class DatosFakeUtils {

    private static Faker faker = new Faker(new Locale("es"));

    public static Foto getFoto(long clienteId) {
        return Foto.builder()
                .clienteId(clienteId)
                .foto(faker.app().name())
                .build();
    }

    public static Foto getFoto() {
        return Foto.builder()
                .clienteId(faker.number().numberBetween(1L, 10000000000000000L))
                .foto(faker.app().name())
                .build();
    }
}
