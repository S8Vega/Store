package com.pragma.serviciocliente.infraestructura.rest;

import com.pragma.serviciocliente.dominio.Foto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "servicio-foto")
public interface FotoRest {
    @GetMapping("/foto/{clienteId}")
    ResponseEntity<Foto> obtenerPorClienteId(@PathVariable Long clienteId) throws Exception;

    @PostMapping("/foto")
    ResponseEntity<Foto> guardar(@RequestBody Foto foto);

    @DeleteMapping("/foto/{clienteId}")
    ResponseEntity<Foto> eliminar(@PathVariable Long clienteId);

    @PostMapping("/foto/clientes")
    ResponseEntity<List<Foto>> obtenerPorClienteIds(@RequestBody List<Long> clienteId);
}
