package com.practice.fileservice.service;

import com.practice.fileservice.FileTestDataBuilder;
import com.practice.fileservice.model.File;
import com.practice.fileservice.repository.FileRepositoryInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FileServiceTest {

    @InjectMocks
    private FileService fileService;

    @Mock
    private FileRepositoryInterface fileRepository;

    @Test
    void findById() throws ClassNotFoundException {
        File fileExpected = FileTestDataBuilder.fileBuilder();
        when(fileRepository.findById(fileExpected.getId())).thenReturn(fileExpected);

        File fileActual = fileService.findById(fileExpected.getId());

        assertEquals(fileExpected, fileActual);
    }

    @Test
    void save() {
        File fileExpected = FileTestDataBuilder.fileBuilder();
        when(fileRepository.save(fileExpected)).thenReturn(fileExpected);

        File fileActual = fileService.save(fileExpected);

        assertEquals(fileExpected, fileActual);
    }

    @Test
    void delete() {
        File fileExpected = FileTestDataBuilder.fileBuilder();

        assertDoesNotThrow(() -> fileService.delete(fileExpected.getId()));
    }

    @Test
    void findByIds() throws ClassNotFoundException {
        File fileExpected = FileTestDataBuilder.fileBuilder();
        when(fileRepository.findById(anyString())).thenReturn(fileExpected);

        List<File> filesActual = fileService.findByIds(List.of(fileExpected.getId(), fileExpected.getId()));

        assertEquals(List.of(fileExpected, fileExpected), filesActual);
    }

    @Test
    void findByIds_error() throws ClassNotFoundException {
        File fileExpected = FileTestDataBuilder.fileBuilder();
        when(fileRepository.findById(anyString())).thenThrow(new ClassNotFoundException());

        List<File> filesActual = fileService.findByIds(List.of(fileExpected.getId(), fileExpected.getId()));

        assertEquals(List.of(), filesActual);
    }
}