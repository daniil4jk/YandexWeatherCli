package ru.daniil4jk.yweather.cli;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public final class WeatherClient {

    private static final String BASE_URL = "https://api.weather.yandex.ru/v2/forecast";
    private static final HttpClient http = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();

    public static Forecast fetch(CliArgs cfg) throws Exception {
        String url = BASE_URL
            + "?lat=" + cfg.lat()
            + "&lon=" + cfg.lon()
            + "&lang=ru_RU"
            + "&limit=" + Math.max(1, cfg.days())
            + "&hours=true"
            + "&extra=true";

        HttpRequest req = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("X-Yandex-Weather-Key", CliArgs.DEFAULT_KEY)
            .GET()
            .build();

        HttpResponse<String> resp = http.send(req, HttpResponse.BodyHandlers.ofString());

        if (resp.statusCode() != 200) {
            System.err.println("API error " + resp.statusCode() + ": " + resp.body());
            System.exit(1);
        }

        return Forecast.fromJson(mapper.readTree(resp.body()));
    }
}
