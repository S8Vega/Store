package com.practice.fileservice.service;

import com.practice.fileservice.model.File;
import com.practice.fileservice.repository.FileRepositoryInterface;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class FileService {

    @Autowired
    private FileRepositoryInterface fileRepository;

    public File findById(String id) throws ClassNotFoundException {
        log.info("findById: " + id);
        return fileRepository.findById(id);
    }

    public File save(File file) {
        log.info("save: " + file);
        return fileRepository.save(file);
    }

    public void delete(String id) {
        log.info("delete: " + id);
        fileRepository.delete(id);
    }

    public List<File> findByIds(List<String> ids) {
        log.info("findByIds: " + ids);
        List<File> files = new ArrayList<>();
        for (String id : ids) {
            try {
                File file = findById(id);
                files.add(file);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        return files;
    }
}
