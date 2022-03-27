package com.practice.fileservice.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class FileTest {

	@Test
	void constructor() {
		File file = new File();

		assertNull(file.getFileName());
		assertNull(file.getFileType());
		assertNull(file.getBase64());
	}

}