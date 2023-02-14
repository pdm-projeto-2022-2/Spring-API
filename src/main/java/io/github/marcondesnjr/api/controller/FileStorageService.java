package io.github.marcondesnjr.api.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class FileStorageService {

    private final Path root = Paths.get("/tmp/uploads");

    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    public String save(MultipartFile file) {
        try {
            var filename = UUID.randomUUID();
            var split = file.getOriginalFilename().split("\\.");
            var extension = split[split.length-1];
            Files.copy(file.getInputStream(), this.root.resolve(filename+"."+extension));
            return filename+"."+extension;
        } catch (Exception e) {
                       throw new RuntimeException(e.getMessage());
        }
    }

}
