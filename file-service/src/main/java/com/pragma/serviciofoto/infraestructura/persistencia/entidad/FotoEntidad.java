package com.pragma.serviciofoto.infraestructura.persistencia.entidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "foto")
public class FotoEntidad {
    @Id
    private String id;
    @Field
    private Long clienteId;
    @Field
    private String foto;

    public interface Atributos {
        String ID = "id";
        String CLIENTE_ID = "clienteId";
        String FOTO = "foto";
    }
}