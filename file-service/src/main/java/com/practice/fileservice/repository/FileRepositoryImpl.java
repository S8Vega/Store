package com.practice.fileservice.repository;

import com.practice.fileservice.dao.FileDaoInterface;
import com.practice.fileservice.entity.FileEntity;
import com.practice.fileservice.error.ErrorUtils;
import com.practice.fileservice.mapper.FileMapper;
import com.practice.fileservice.model.File;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Log4j2
@Repository
public class FileRepositoryImpl implements FileRepositoryInterface {

	@Autowired
	private FileDaoInterface fileDao;
	@Autowired
	private FileMapper fileMapper;

	@Override
	public File findById(String id) throws ClassNotFoundException {
		log.info("findById: " + id);
		Optional<FileEntity> fileEntity = fileDao.findById(id);
		if (fileEntity.isEmpty()) {
			log.error("File not found");
			throw new ClassNotFoundException(ErrorUtils.nonExistentFile(id));
		}
		return fileMapper.entityToModel(fileEntity.get());
	}

	@Override
	public File save(File file) {
		log.info("save: " + file);
		FileEntity fileEntity = fileMapper.modelToEntity(file);
		return fileMapper.entityToModel(fileDao.save(fileEntity));
	}

	@Override
	public void delete(String id) {
		log.info("delete: " + id);
		fileDao.deleteById(id);
	}
}
