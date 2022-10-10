package com.pragma.serviciocliente.infraestructura.persistencia.entidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
