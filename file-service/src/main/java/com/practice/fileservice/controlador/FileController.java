package com.practice.fileservice.controlador;

import com.practice.fileservice.model.File;
import com.practice.fileservice.service.FileService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8000")
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("/{id}")
    public ResponseEntity<File> findById(@PathVariable String id) throws NotFoundException {
        return ResponseEntity.ok(fileService.findById(id));
    }

    @PostMapping
    public ResponseEntity<File> save(@RequestBody File file) {
        File newFile = fileService.save(file);
        return new ResponseEntity<>(newFile, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<File> delete(@PathVariable String id) {
        fileService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/list")
    public ResponseEntity<List<File>> findByIds(@RequestBody List<String> ids) {
        return ResponseEntity.ok(fileService.findByIds(ids));
    }
}
