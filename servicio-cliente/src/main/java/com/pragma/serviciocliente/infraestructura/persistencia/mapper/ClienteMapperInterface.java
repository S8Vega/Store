package com.pragma.serviciocliente.infraestructura.persistencia.mapper;

import com.pragma.serviciocliente.dominio.Cliente;
import com.pragma.serviciocliente.dominio.Foto;
import com.pragma.serviciocliente.infraestructura.persistencia.entidad.ClienteEntidad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ClienteMapperInterface {

    @Mappings({
            @Mapping(target = Cliente.Atributos.NOMBRES, source = "clienteEntidad." + ClienteEntidad.Atributos.NOMBRES),
            @Mapping(target = Cliente.Atributos.APELLIDOS, source = "clienteEntidad." + ClienteEntidad.Atributos.APELLIDOS),
            @Mapping(target = Cliente.Atributos.TIPO_IDENTIFICACION, source = "clienteEntidad." + ClienteEntidad.Atributos.TIPO_IDENTIFICACION),
            @Mapping(target = Cliente.Atributos.NUMERO_IDENTIFICACION, source = "clienteEntidad." + ClienteEntidad.Atributos.NUMERO_IDENTIFICACION),
            @Mapping(target = Cliente.Atributos.EDAD, source = "clienteEntidad." + ClienteEntidad.Atributos.EDAD),
            @Mapping(target = Cliente.Atributos.CIUDAD_NACIMIENTO, source = "clienteEntidad." + ClienteEntidad.Atributos.CIUDAD),
            @Mapping(target = Cliente.Atributos.FOTO, source = "fotoEntidad." + Foto.Atributos.FOTO)
    })
    Cliente aCliente(ClienteEntidad clienteEntidad, Foto fotoEntidad);
}
