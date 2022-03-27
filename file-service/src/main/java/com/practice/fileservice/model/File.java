package com.practice.fileservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class File {

	private String id;
	private String base64;
	private String fileName;
	private String fileType;

	public static class Attributes {
		public static final String ID = "id";
		public static final String BASE64 = "base64";
		public static final String FILE_NAME = "fileName";
		public static final String FILE_TYPE = "fileType";

		private Attributes() {
			throw new IllegalStateException("Utility class");
		}
	}
}
