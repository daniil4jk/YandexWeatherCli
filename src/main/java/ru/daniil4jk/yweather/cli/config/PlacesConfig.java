package ru.daniil4jk.yweather.cli.config;

public class PlacesConfig extends SomeConfig {
    private static final String PLACES_FILE_NAME = "places.json";
    public PlacesConfig() {
        super(PLACES_FILE_NAME);
    }
}
