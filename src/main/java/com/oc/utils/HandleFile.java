package com.oc.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HandleFile {
    public static String saveFile(MultipartFile file) throws IOException {

        String rootDir = System.getProperty("user.dir") + "/src/main/resources/static/assets/images/";

        // Créer le répertoire s'il n'existe pas
        Path directoryPath = Paths.get(rootDir);
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }
        String originalFileName = file.getOriginalFilename();
        assert originalFileName != null;
        Path filePath = directoryPath.resolve(originalFileName);
        Files.write(filePath, file.getBytes());

        return originalFileName;
    }
}
