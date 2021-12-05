package com.practice.fileservice.mapper;

import com.practice.fileservice.entity.FileEntity;
import com.practice.fileservice.model.File;
import org.springframework.stereotype.Component;

@Component
public class FileMapper {

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
