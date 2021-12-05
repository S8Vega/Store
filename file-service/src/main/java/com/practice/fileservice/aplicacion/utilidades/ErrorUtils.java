package com.practice.fileservice.aplicacion.utilidades;

public class ErrorUtils {

    private static final String NO_TIENE_FOTO = "el cliente con id %d no tiene foto";

    public static String clienteSinFoto(Long clienteId) {
        return String.format(NO_TIENE_FOTO, clienteId);
    }

}
