package com.practice.fileservice.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class FileEntityTest {

    @Test
    void constructor() {
        FileEntity fileEntity = new FileEntity();

        assertNotNull(fileEntity);
        assertNull(fileEntity.getId());
        assertNull(fileEntity.getFileName());
        assertNull(fileEntity.getFileType());
        assertNull(fileEntity.getBase64());
    }

}