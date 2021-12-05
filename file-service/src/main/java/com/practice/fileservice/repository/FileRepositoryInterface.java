package com.practice.fileservice.repository;

import com.practice.fileservice.model.File;
import javassist.NotFoundException;

public interface FileRepositoryInterface {
    File findById(String id) throws NotFoundException;

    File save(File file);

    void delete(String id);
}
