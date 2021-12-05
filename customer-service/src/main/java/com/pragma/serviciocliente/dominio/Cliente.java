package com.pragma.serviciocliente.dominio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {
    private String nombres;
    private String apellidos;
    private TipoIdentifiacion tipoIdentificacion;
    private String numeroIdentificacion;
    private Integer edad;
    private String ciudadNacimiento;
    private String foto;

    public interface Atributos {
        String NOMBRES = "nombres";
        String APELLIDOS = "apellidos";
        String TIPO_IDENTIFICACION = "tipoIdentificacion";
        String NUMERO_IDENTIFICACION = "numeroIdentificacion";
        String EDAD = "edad";
        String CIUDAD_NACIMIENTO = "ciudadNacimiento";
        String FOTO = "foto";
    }
}
