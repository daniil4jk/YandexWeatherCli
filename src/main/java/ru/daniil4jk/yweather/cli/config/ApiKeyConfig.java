package ru.daniil4jk.yweather.cli.config;

public final class ApiKeyConfig extends SomeConfig {
    private static final String API_KEY_FILE_NAME = "api-key.txt";
    public ApiKeyConfig() {
        super(API_KEY_FILE_NAME);
    }
}
