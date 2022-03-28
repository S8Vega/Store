package com.practice.fileservice.controller;

import com.practice.fileservice.model.File;
import com.practice.fileservice.service.FileService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8000")
@Log4j2
@RequestMapping("/file")
public class FileController {

	@Autowired
	private FileService fileService;

	@GetMapping("/{id}")
	public ResponseEntity<File> findById(@PathVariable String id) throws ClassNotFoundException {
		log.info("findById: " + id);
		return ResponseEntity.ok(fileService.findById(id));
	}

	@PostMapping
	public ResponseEntity<File> save(@RequestBody File file) {
		log.info("save: " + file);
		File newFile = fileService.save(file);
		return new ResponseEntity<>(newFile, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<File> delete(@PathVariable String id) {
		log.info("delete: " + id);
		fileService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/list")
	public ResponseEntity<List<File>> findByIds(@RequestBody List<String> ids) {
		log.info("findByIds: " + ids);
		return ResponseEntity.ok(fileService.findByIds(ids));
	}
}
