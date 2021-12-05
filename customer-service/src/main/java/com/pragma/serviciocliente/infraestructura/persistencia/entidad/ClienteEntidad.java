package com.pragma.serviciocliente.infraestructura.persistencia.entidad;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cliente")
public class ClienteEntidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String nombres;
    @NonNull
    private String apellidos;
    @OneToOne(mappedBy = "clienteEntidad", fetch = FetchType.LAZY)
    private IdentificacionEntidad identificacionEntidad;
    @NonNull
    private Integer edad;
    @ManyToOne
    @JoinColumn(name = "ciudad_id")
    private CiudadEntidad ciudadDeNacimiento;

    public interface Atributos {
        String ID = "id";
        String NOMBRES = "nombres";
        String APELLIDOS = "apellidos";
        String IDENTIFICACION_ENTIDAD = "identificacionEntidad";
        String EDAD = "edad";
        String CIUDAD_ENTIDAD = "ciudadDeNacimiento";
        String CIUDAD = Atributos.CIUDAD_ENTIDAD + "." + CiudadEntidad.Atributos.NOMBRE;
        String TIPO_IDENTIFICACION = Atributos.IDENTIFICACION_ENTIDAD + "." + IdentificacionEntidad.Atributos.TIPO;
        String NUMERO_IDENTIFICACION = Atributos.IDENTIFICACION_ENTIDAD + "." + IdentificacionEntidad.Atributos.NUMERO;
    }
}
