package com.pragma.serviciocliente.infraestructura.controlador;

import com.pragma.serviciocliente.dominio.Cliente;
import com.pragma.serviciocliente.dominio.servicio.ClienteServicio;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8000")
@RequestMapping("/cliente")
public class ClienteControlador {

    @Autowired
    private ClienteServicio clienteServicio;

    @GetMapping("/identificacion/{tipo}/{numero}")
    @ApiOperation("obtiene un cliente dado su tipo y numero de identificacion")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<Cliente> obtenerPorIdentificacion(
            @PathVariable
            @ApiParam(value = "tipo de identificacion", required = true, example = "CEDULA")
                    String tipo,
            @PathVariable
            @ApiParam(value = "numero de identificacion", required = true, example = "1")
                    String numero
    ) throws Exception {
        Cliente cliente = clienteServicio.obtenetPorIdentificacion(tipo, numero);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @GetMapping("/edad/{edad}")
    @ApiOperation("obtiene una lista de los clientes cuya edad sea mayor o igual a el parametro")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "no hay ningun cliente que cumpla la condicion")
    })
    public ResponseEntity<List<Cliente>> obtenerPorEdadMayorIgual(
            @PathVariable
            @ApiParam(value = "edad", required = true, example = "20")
                    Integer edad
    ) throws Exception {
        List<Cliente> clientes = clienteServicio.obtenerPorEdadMayorIgual(edad);
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation("obtiene una lista de todos los clientes ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "no hay ningun cliente registrado")
    })
    public ResponseEntity<List<Cliente>> obtenerTodos() throws Exception {
        List<Cliente> clientes = clienteServicio.obtenerTodos();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("guarda un cliente")
    @ApiResponse(code = 201, message = "CREATED")
    public ResponseEntity<Cliente> guardar(
            @RequestBody
            @ApiParam(value = "cliente", required = true)
                    Cliente cliente
    ) throws Exception {
        clienteServicio.guardar(cliente);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @ApiOperation("actualiza un cliente")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<Cliente> actualizar(
            @RequestBody
            @ApiParam(value = "cliente", required = true)
                    Cliente cliente
    ) throws Exception {
        clienteServicio.actualizar(cliente);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/identificacion/{tipo}/{numero}")
    @ApiOperation("elimina un cliente")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<Cliente> eliminar(
            @PathVariable
            @ApiParam(value = "tipo de identificacion", required = true, example = "CEDULA")
                    String tipo,
            @PathVariable
            @ApiParam(value = "numero de identificacion", required = true, example = "1")
                    String numero
    ) throws Exception {
        clienteServicio.eliminar(tipo, numero);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
