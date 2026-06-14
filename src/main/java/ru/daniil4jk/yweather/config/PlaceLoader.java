package ru.daniil4jk.yweather.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.daniil4jk.yweather.model.Place;

import java.util.List;

public final class PlaceLoader {

    private static final String FILE_NAME = "places.json";

    private final ObjectMapper mapper;
    private final String placesJson;

    public PlaceLoader(ObjectMapper mapper, String placesJson) {
        this.mapper = mapper;
        this.placesJson = placesJson;
    }

    public List<Place> load() throws Exception {
        return mapper.readValue(placesJson, new TypeReference<List<Place>>() {});
    }

    public Place findByName(String name) throws Exception {
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
        return null;
    }
}
