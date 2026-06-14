package ru.daniil4jk.yweather.cli;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.daniil4jk.yweather.cli.config.ApiKeyConfig;
import ru.daniil4jk.yweather.cli.config.PlacesConfig;

import java.nio.file.Files;
import java.nio.file.Path;

public class YandexWeatherCli {

    public static void main(String[] args) throws Exception {
        var objectMapper = new ObjectMapper();
        String places = new PlacesConfig().read();
        var placeLoader = new PlaceLoader(objectMapper, places);

        var cfg = new CliArgParser(placeLoader).parse(args);
        var forecastParser = new ForecastParser();
        String apiKey = new ApiKeyConfig().read();
        var weatherClient = new WeatherClient(apiKey, forecastParser);
        var forecast = weatherClient.fetch(cfg);

        var conditionMapper = new ConditionMapper();
        var weatherFormatter = new WeatherFormatter(conditionMapper);
        weatherFormatter.printCompact(forecast, cfg);
    }
}
