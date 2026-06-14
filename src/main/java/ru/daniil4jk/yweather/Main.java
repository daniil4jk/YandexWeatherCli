package ru.daniil4jk.yweather;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.daniil4jk.yweather.cli.CliArgParser;
import ru.daniil4jk.yweather.client.ForecastParser;
import ru.daniil4jk.yweather.client.WeatherClient;
import ru.daniil4jk.yweather.config.ApiKeyConfig;
import ru.daniil4jk.yweather.config.PlaceLoader;
import ru.daniil4jk.yweather.config.PlacesConfig;
import ru.daniil4jk.yweather.format.ConditionMapper;
import ru.daniil4jk.yweather.format.WeatherFormatter;

public class Main {

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
