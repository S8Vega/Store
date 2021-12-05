package com.practice.fileservice.dao;

import com.practice.fileservice.entity.FileEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileDaoInterface extends MongoRepository<FileEntity, String> {
}
