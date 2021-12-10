package com.practice.fileservice.controller;

import com.practice.fileservice.FileTestDataBuilder;
import com.practice.fileservice.model.File;
import com.practice.fileservice.service.FileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FileControllerTest {

    @InjectMocks
    private FileController controller;

    @Mock
    private FileService service;

    @Test
    void findById() throws ClassNotFoundException {
        ResponseEntity<File> fileResponseEntity = ResponseEntity.ok(FileTestDataBuilder.fileBuilder());
        when(service.findById(fileResponseEntity.getBody().getId())).thenReturn(fileResponseEntity.getBody());

        ResponseEntity<File> response = controller.findById(fileResponseEntity.getBody().getId());

        assertEquals(fileResponseEntity, response);
    }

    @Test
    void save() {
        ResponseEntity<File> fileResponseEntity = new ResponseEntity<File>(FileTestDataBuilder.fileBuilder(), HttpStatus.CREATED);
        when(service.save(fileResponseEntity.getBody())).thenReturn(fileResponseEntity.getBody());

        ResponseEntity<File> response = controller.save(fileResponseEntity.getBody());

        assertEquals(fileResponseEntity, response);
    }

    @Test
    void delete() {
        ResponseEntity<File> file = ResponseEntity.ok(FileTestDataBuilder.fileBuilder());

        ResponseEntity<File> response = controller.delete(file.getBody().getId());

        assertEquals(new ResponseEntity<>(HttpStatus.OK), response);
    }

    @Test
    void findByIds() {
        List<String> ids = List.of("1", "2", "3");
        List<File> fileResponseEntity = List.of(FileTestDataBuilder.fileBuilder(), FileTestDataBuilder.fileBuilder(), FileTestDataBuilder.fileBuilder());
        when(service.findByIds(ids)).thenReturn(fileResponseEntity);

        ResponseEntity<List<File>> response = controller.findByIds(ids);

        assertEquals(ResponseEntity.ok(fileResponseEntity), response);
    }
}