package com.pragma.serviciocliente.aplicacion.utilidades;

public class StringUtils {

    public static final String NO_HAY_FOTO = "no hay foto :(";
    private static final String LETRAS_MINUSCULAS = ".*[a-z].*";
    private static final String LETRAS_MAYUSCULAS = ".*[A-Z].*";

    public static boolean contieneLetras(String texto) {
        return texto.matches(LETRAS_MINUSCULAS) || texto.matches(LETRAS_MAYUSCULAS);
    }
}
