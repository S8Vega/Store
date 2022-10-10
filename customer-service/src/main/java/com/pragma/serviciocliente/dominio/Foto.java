package com.pragma.serviciocliente.dominio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Foto {
    Long id;
    String base64;
    String fileName;
    String fileType;

    public interface Atributos {
        String CLIENTE_ID = "id";
        String FOTO = "base64";
    }
}
