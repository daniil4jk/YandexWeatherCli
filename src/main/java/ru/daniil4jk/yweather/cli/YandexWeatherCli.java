package ru.daniil4jk.yweather.cli;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;

public class YandexWeatherCli {

    public static void main(String[] args) throws Exception {
        var conditionMapper = new ConditionMapper();
        var objectMapper = new ObjectMapper();
        var placeLoader = new PlaceLoader(objectMapper);
        var apiKey = Files.readString(Path.of("api-key.txt")).trim();
        var forecastParser = new ForecastParser();
        var weatherClient = new WeatherClient(apiKey, forecastParser);
        var weatherFormatter = new WeatherFormatter(conditionMapper);

        var cfg = new CliArgParser(placeLoader).parse(args);
        var forecast = weatherClient.fetch(cfg);
        weatherFormatter.printCompact(forecast, cfg);
    }
}
