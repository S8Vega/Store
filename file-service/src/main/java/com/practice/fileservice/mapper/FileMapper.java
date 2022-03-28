package com.practice.fileservice.mapper;

import com.practice.fileservice.entity.FileEntity;
import com.practice.fileservice.model.File;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class FileMapper {

	public File entityToModel(FileEntity fileEntity) {
		log.info("entityToModel");
		return File.builder()
				.id(fileEntity.getId())
				.base64(fileEntity.getBase64())
				.fileName(fileEntity.getFileName())
				.fileType(fileEntity.getFileType())
				.build();
	}

	public FileEntity modelToEntity(File file) {
		log.info("modelToEntity");
		return FileEntity.builder()
				.id(file.getId())
				.base64(file.getBase64())
				.fileName(file.getFileName())
				.fileType(file.getFileType())
				.build();
	}
}
