package com.pragma.serviciofoto.dominio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Foto {
    Long clienteId;
    String foto;

    public interface Atributos {
        String CLIENTE_ID = "clienteId";
        String FOTO = "foto";
    }
}
