package com.pragma.serviciocliente.infraestructura.persistencia.entidad;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ciudad")
public class CiudadEntidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String nombre;
    @OneToMany(mappedBy = "ciudadDeNacimiento", fetch = FetchType.LAZY)
    private List<ClienteEntidad> clientesEntidad;

    public interface Atributos {
        String ID = "id";
        String NOMBRE = "nombre";
        String CLIENTES_ENTIDAD = "clientesEntidad";
    }
}
