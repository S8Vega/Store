package com.practice.fileservice.repository;

import com.practice.fileservice.dao.FileDaoInterface;
import com.practice.fileservice.entity.FileEntity;
import com.practice.fileservice.error.ErrorUtils;
import com.practice.fileservice.mapper.FileMapper;
import com.practice.fileservice.model.File;
import javassist.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class FileRepositoryImpl implements FileRepositoryInterface {

    private static final Logger LOGGER = LogManager.getLogger(FileRepositoryImpl.class);

    @Autowired
    private FileDaoInterface fileDao;
    @Autowired
    private FileMapper fileMapper;

    @Override
    public File findById(String id) throws NotFoundException {
        Optional<FileEntity> fileEntity = fileDao.findById(id);
        if (fileEntity.isEmpty()) {
            LOGGER.error(ErrorUtils.nonExistentFile(id));
            throw new NotFoundException(ErrorUtils.nonExistentFile(id));
        }
        return fileMapper.entityToModel(fileEntity.get());
    }

    @Override
    public File save(File file) {
        FileEntity fileEntity = fileMapper.modelToEntity(file);
        return fileMapper.entityToModel(fileDao.save(fileEntity));
    }

    @Override
    public void delete(String id) {
        fileDao.deleteById(id);
    }
}
