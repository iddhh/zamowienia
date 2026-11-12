package org.zamowienia;

import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class LoadFiles {
    public final String directory;

    public LoadFiles(String directory) {
        this.directory = directory;
    }

    public Map<String, String>  loadingfiles() {
        System.out.println("Szukanie plików o formacie JSON w katalogu: " + directory);
        Map<String, String> fileSet = new HashMap<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(directory), "*.json")) {
            for (Path path : stream) {
                if (!Files.isDirectory(path)) {

                    String filename = path.getFileName().toString();
                    String jsontext = FileUtils.readFileToString(path.toFile(), StandardCharsets.UTF_8);
                    fileSet.put(filename, jsontext);
                }
            }
        }catch (IOException e) {
            System.err.println("Bład w szukaniu plików");
        }
        return fileSet;
    }
}
