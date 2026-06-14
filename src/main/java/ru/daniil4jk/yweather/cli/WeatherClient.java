package ru.daniil4jk.yweather.cli;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public final class WeatherClient {

    private static final String BASE_URL = "https://api.weather.yandex.ru/v2/forecast";

    private final String apiKey;
    private final ForecastParser forecastParser;
    private final HttpClient httpClient;
    private final ObjectMapper mapper;

    public WeatherClient(String apiKey, ForecastParser forecastParser) {
        this.apiKey = apiKey;
        this.forecastParser = forecastParser;
        this.httpClient = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
    }

    WeatherClient(String apiKey, ForecastParser forecastParser, HttpClient httpClient, ObjectMapper mapper) {
        this.apiKey = apiKey;
        this.forecastParser = forecastParser;
        this.httpClient = httpClient;
        this.mapper = mapper;
    }

    public Forecast fetch(CliArgs cfg) throws Exception {
        String url = BASE_URL
                + "?lat=" + cfg.lat()
                + "&lon=" + cfg.lon()
                + "&lang=ru_RU"
                + "&limit=" + Math.max(1, cfg.days())
                + "&hours=true"
                + "&extra=true";

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("X-Yandex-Weather-Key", apiKey)
                .GET()
                .build();

        HttpResponse<String> resp = httpClient.send(req, HttpResponse.BodyHandlers.ofString());

        if (resp.statusCode() != 200) {
            System.err.println("API error " + resp.statusCode() + ": " + resp.body());
            System.exit(1);
        }

        return forecastParser.parse(mapper.readTree(resp.body()));
    }
}
