package com.practice.fileservice.service;

import com.practice.fileservice.model.File;
import com.practice.fileservice.repository.FileRepositoryInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    private static final Logger LOGGER = LogManager.getLogger(FileService.class);

    @Autowired
    private FileRepositoryInterface fileRepository;

    public File findById(String id) throws ClassNotFoundException {
        return fileRepository.findById(id);
    }

    public File save(File file) {
        return fileRepository.save(file);
    }

    public void delete(String id) {
        fileRepository.delete(id);
    }

    public List<File> findByIds(List<String> ids) {
        List<File> files = new ArrayList<>();
        for (String id : ids) {
            try {
                File file = findById(id);
                files.add(file);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }
        return files;
    }
}
