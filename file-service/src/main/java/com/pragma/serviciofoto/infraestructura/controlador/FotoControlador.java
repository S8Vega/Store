package com.pragma.serviciofoto.infraestructura.controlador;

import com.pragma.serviciofoto.dominio.Foto;
import com.pragma.serviciofoto.dominio.servicio.FotoServicio;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8000")
@RequestMapping("/foto")
public class FotoControlador {
    @Autowired
    private FotoServicio fotoServicio;

    @GetMapping("/{clienteId}")
    public ResponseEntity<Foto> obtenerPorClienteId(@PathVariable Long clienteId) throws NotFoundException {
        return new ResponseEntity<>(fotoServicio.buscarPorClienteId(clienteId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Foto> guardar(@RequestBody Foto foto) {
        fotoServicio.guardar(foto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Foto> eliminar(@PathVariable Long clienteId) {
        fotoServicio.eliminar(clienteId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/clientes")
    public ResponseEntity<List<Foto>> obtenerPorClienteIds(@RequestBody List<Long> clienteId) {
        return new ResponseEntity<>(fotoServicio.buscarPorClienteIds(clienteId), HttpStatus.OK);
    }
}
