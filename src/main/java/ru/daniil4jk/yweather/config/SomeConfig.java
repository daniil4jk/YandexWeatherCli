package ru.daniil4jk.yweather.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class SomeConfig {
    private final String fileName;

    protected SomeConfig(String fileName) {
        this.fileName = fileName;
    }

    private File findFile() {
        try {
            var location = SomeConfig.class
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI();
            Path jarDir = Paths.get(location).getParent();
            if (jarDir != null) {
                File f = jarDir.resolve(fileName).toFile();
                if (f.isFile()) return f;
            }
        } catch (Exception ignored) {
        }
        File f = Paths.get("").resolve(fileName).toFile();
        if (f.isFile()) return f;
        return null;
    }

    public File load() {
        File file = findFile();
        if (file == null) {
            System.err.println("Файл " + fileName + " не найден ни рядом с JAR, ни в текущей папке.");
            System.exit(1);
        }
        return file;
    }

    public String read() {
        try {
            return Files.readString(load().toPath());
        } catch (IOException e) {
            System.err.println("Файл " + fileName + " найден, но недоступен для чтения.");
            System.exit(1);
            return null;
        }
    }
}
