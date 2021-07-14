package com.pragma.serviciocliente.infraestructura.persistencia.entidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "identificacion")
public class IdentificacionEntidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipo;
    private String numero;
    @OneToOne
    @JoinColumn(name = "cliente_id", unique = true)
    private ClienteEntidad clienteEntidad;

    public interface Atributos {
        String ID = "id";
        String TIPO = "tipo";
        String NUMERO = "numero";
        String CLIENTE_ENTIDAD = "clienteEntidad";
    }
}
