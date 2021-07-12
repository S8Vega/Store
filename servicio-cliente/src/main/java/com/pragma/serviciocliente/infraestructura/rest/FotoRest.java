package com.pragma.serviciocliente.infraestructura.rest;

import com.pragma.serviciocliente.dominio.Foto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "servicio-foto", url = "http://localhost:8002/foto")
public interface FotoRest {
    @GetMapping("/{clienteId}")
    ResponseEntity<Foto> obtenerPorClienteId(@PathVariable Long clienteId) throws Exception;

    @PostMapping
    ResponseEntity<Foto> guardar(@RequestBody Foto foto);

    @DeleteMapping("/{clienteId}")
    ResponseEntity<Foto> eliminar(@PathVariable Long clienteId);

    @PostMapping("/clientes")
    ResponseEntity<List<Foto>> obtenerPorClienteIds(@RequestBody List<Long> clienteId);
}
