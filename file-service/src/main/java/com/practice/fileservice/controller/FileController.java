package com.practice.fileservice.controller;

import com.practice.fileservice.model.File;
import com.practice.fileservice.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@Log4j2
@RequestMapping("/file")
public class FileController {

	@Autowired
	private FileService fileService;

	@Operation(summary = "Obtiene un archivo por su id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Archivo buscado correctamente",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = File.class))}),
			@ApiResponse(responseCode = "404", description = "Archivo no encontrado")})
	@GetMapping("/{id}")
	public ResponseEntity<File> findById(@Parameter(description = "ID del archivo", required = true)
	                                     @PathVariable String id) throws ClassNotFoundException {
		log.info("findById: " + id);
		return ResponseEntity.ok(fileService.findById(id));
	}

	@Operation(summary = "Guarda un archivo")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Archivo guardado correctamente",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = File.class))})})
	@PostMapping
	public ResponseEntity<File> save(@Parameter(description = "Archivo a guardar", required = true)
	                                 @RequestBody File file) {
		log.info("save: " + file);
		File newFile = fileService.save(file);
		return new ResponseEntity<>(newFile, HttpStatus.CREATED);
	}

	@Operation(summary = "Elimina un archivo por su id")
	@ApiResponse(responseCode = "200", description = "Archivo eliminado correctamente")
	@DeleteMapping("/{id}")
	public ResponseEntity<File> delete(@Parameter(description = "ID del archivo", required = true)
	                                   @PathVariable String id) {
		log.info("delete: " + id);
		fileService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Operation(summary = "Obtiene los archivos de los id enviados")
	@ApiResponse(responseCode = "200", description = "Archivos obtenidos correctamente")
	@PostMapping("/list")
	public ResponseEntity<List<File>> findByIds(@Parameter(description = "Lista de IDs de archivos", required = true)
	                                            @RequestBody List<String> ids) {
		log.info("findByIds: " + ids);
		return ResponseEntity.ok(fileService.findByIds(ids));
	}
}
