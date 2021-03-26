package kz.dilau.htc.filemanager.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtil {
    private FileUtil() {}

    public static void saveFile(Path filePath, byte[] data) throws IOException {
        if (Files.exists(filePath)) {
            throw new IOException("File already exists");
        }
        else {
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
            Files.write(filePath, data);
        }
    }

    public static void readFile(Path filePath, OutputStream os) throws IOException {
        if (Files.exists(filePath)) {
            Files.copy(filePath, os);
        }
        else {
            throw new IOException("File not found");
        }
    }

    public static byte[] readFile(Path filePath) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            readFile(filePath, baos);
            return baos.toByteArray();
        }
    }

    public static void deleteFile(Path filePath) throws IOException {
        Files.deleteIfExists(filePath);
    }
}
