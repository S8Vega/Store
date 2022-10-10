package com.practice.fileservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class File {

    @Schema(description = "ID del archivo", example = "1", required = false)
    private String id;
    @Schema(description = "Archivo en base64", example = "sakdljfhlaskdjbvailsudhf", required = true)
    private String base64;
    @Schema(description = "Nombre del archivo", example = "archivo.txt", required = true)
    private String fileName;
    @Schema(description = "Tipo de archivo", example = "txt", required = true)
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
