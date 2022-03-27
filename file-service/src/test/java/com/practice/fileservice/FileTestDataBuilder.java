package com.practice.fileservice;

import com.practice.fileservice.entity.FileEntity;
import com.practice.fileservice.model.File;

public class FileTestDataBuilder {

	private static final String ID = "1";
	private static final String BASE64 = "base64";
	private static final String FILE_NAME = "fileName";
	private static final String FILE_TYPE = "fileType";

	public static File fileBuilder() {
		return File.builder()
				.id(ID)
				.base64(BASE64)
				.fileName(FILE_NAME)
				.fileType(FILE_TYPE)
				.build();
	}

	public static FileEntity fileEntityBuilder() {
		return FileEntity.builder()
				.id(ID)
				.base64(BASE64)
				.fileName(FILE_NAME)
				.fileType(FILE_TYPE)
				.build();
	}
}
