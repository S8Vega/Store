package com.practice.fileservice.mapper;

import com.practice.fileservice.entity.FileEntity;
import com.practice.fileservice.model.File;
import com.practice.fileservice.repository.FileRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileMapper {

    @Autowired
    private FileRepositoryImpl fileRepository;

    public File entityToModel(FileEntity fileEntity) {
        return File.builder()
                .id(fileEntity.getId())
                .base64(fileEntity.getBase64())
                .fileName(fileEntity.getFileName())
                .fileType(fileEntity.getFileType())
                .build();
    }

    public FileEntity modelToEntity(File file) {
        return FileEntity.builder()
                .id(file.getId())
                .base64(file.getBase64())
                .fileName(file.getFileName())
                .fileType(file.getFileType())
                .build();
    }
}
