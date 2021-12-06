package com.practice.fileservice.repository;

import com.practice.fileservice.model.File;

public interface FileRepositoryInterface {
    File findById(String id) throws ClassNotFoundException;

    File save(File file);

    void delete(String id);
}
