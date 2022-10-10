package com.practice.fileservice.repository;

import com.practice.fileservice.FileTestDataBuilder;
import com.practice.fileservice.dao.FileDaoInterface;
import com.practice.fileservice.error.ErrorUtils;
import com.practice.fileservice.mapper.FileMapper;
import com.practice.fileservice.model.File;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FileRepositoryImplTest {

    @InjectMocks
    private FileRepositoryImpl fileRepository;

    @Mock
    private FileDaoInterface fileDao;

    @Mock
    private FileMapper fileMapper;

    @Test
    void findById() throws ClassNotFoundException {
        File fileExpected = FileTestDataBuilder.fileBuilder();
        when(fileDao.findById(fileExpected.getId()))
                .thenReturn(Optional.ofNullable(FileTestDataBuilder.fileEntityBuilder()));
        when(fileMapper.entityToModel(FileTestDataBuilder.fileEntityBuilder()))
                .thenCallRealMethod();

        File fileActual = fileRepository.findById(fileExpected.getId());

        assertEquals(fileExpected, fileActual);
        verify(fileDao).findById(fileExpected.getId());
        verify(fileMapper).entityToModel(FileTestDataBuilder.fileEntityBuilder());
    }

    @Test
    void findByIdOnError() {
        File fileExpected = FileTestDataBuilder.fileBuilder();
        when(fileDao.findById(fileExpected.getId()))
                .thenReturn(Optional.empty());

        ClassNotFoundException exception = assertThrows(ClassNotFoundException.class,
                () -> fileRepository.findById(fileExpected.getId()));

        assertEquals(ErrorUtils.nonExistentFile(fileExpected.getId()), exception.getMessage());
        verify(fileDao).findById(fileExpected.getId());
    }

    @Test
    void save() {
        File fileExpected = FileTestDataBuilder.fileBuilder();
        when(fileMapper.modelToEntity(fileExpected))
                .thenCallRealMethod();
        when(fileDao.save(FileTestDataBuilder.fileEntityBuilder()))
                .thenReturn(FileTestDataBuilder.fileEntityBuilder());
        when(fileMapper.entityToModel(FileTestDataBuilder.fileEntityBuilder()))
                .thenReturn(fileExpected);

        File fileActual = fileRepository.save(fileExpected);

        assertEquals(fileExpected, fileActual);
    }

    @Test
    void delete() {
        File file = FileTestDataBuilder.fileBuilder();

        fileRepository.delete(file.getId());

        verify(fileDao).deleteById(file.getId());
    }
}