package com.pragma.serviciocliente.infraestructura.rest;

import com.pragma.serviciocliente.dominio.Foto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "file-service")
public interface FotoRest {
    @GetMapping("/file-service/file/{clienteId}")
    ResponseEntity<Foto> obtenerPorClienteId(@PathVariable Long clienteId) throws Exception;

    @PostMapping("/file-service/file")
    ResponseEntity<Foto> guardar(@RequestBody Foto foto);

    @DeleteMapping("/file-service/file/{clienteId}")
    ResponseEntity<Foto> eliminar(@PathVariable Long clienteId);

    @PostMapping("/file-service/file/list")
    ResponseEntity<List<Foto>> obtenerPorClienteIds(@RequestBody List<Long> clienteId);
}
