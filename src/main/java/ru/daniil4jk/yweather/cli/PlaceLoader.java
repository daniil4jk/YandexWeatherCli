package ru.daniil4jk.yweather.cli;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Загружает places.json — список известных городов с координатами.
 *
 * Файл ищется:
 * 1. Рядом с JAR-файлом (если запущен из JAR)
 * 2. В текущей рабочей директории
 */
public final class PlaceLoader {

    private static final String FILE_NAME = "places.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    private PlaceLoader() {}

    /**
     * Загружает список городов из places.json.
     */
    public static List<Place> load() throws Exception {
        File file = findFile();
        if (file == null) {
            System.err.println("Файл " + FILE_NAME + " не найден ни рядом с JAR, ни в текущей папке.");
            System.exit(1);
        }
        return mapper.readValue(file, new TypeReference<List<Place>>() {});
    }

    /**
     * Ищет место по имени (регистронезависимо).
     */
    public static Place findByName(String name) throws Exception {
        List<Place> places = load();
        for (Place p : places) {
            for (String n : p.names()) {
                if (n.equalsIgnoreCase(name)) {
                    return p;
                }
            }
        }
        System.err.println("Место \"" + name + "\" не найдено в " + FILE_NAME);
        System.err.println("Доступные места:");
        for (Place p : places) {
            System.err.println("  " + p.names().get(0) + " (" + String.join(", ", p.names()) + ")");
        }
        System.exit(1);
        return null; // unreachable
    }

    private static File findFile() throws Exception {
        // Пытаемся найти рядом с JAR / class-файлом
        try {
            var location = PlaceLoader.class
                .getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .toURI();
            Path jarDir = Paths.get(location).getParent();
            if (jarDir != null) {
                File f = jarDir.resolve(FILE_NAME).toFile();
                if (f.isFile()) return f;
            }
        } catch (Exception ignored) {
            // fallback
        }
        // Fallback: текущая рабочая директория
        File f = Paths.get("").resolve(FILE_NAME).toFile();
        if (f.isFile()) return f;
        return null;
    }
}
