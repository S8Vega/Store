package com.practice.fileservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "file")
public class FileEntity {
    @Id
    private String id;
    @Field
    private String base64;
    @Field
    private String fileName;
    @Field
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