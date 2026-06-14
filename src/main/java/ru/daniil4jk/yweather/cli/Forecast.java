package ru.daniil4jk.yweather.cli;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.List;

public final class Forecast {
    private final List<DayForecast> days;

    public Forecast(List<DayForecast> days) {
        this.days = days;
    }

    public static Forecast fromJson(JsonNode root) {
        JsonNode forecasts = root.get("forecasts");
        List<DayForecast> days = new ArrayList<>();
        if (forecasts != null && forecasts.isArray()) {
            for (JsonNode day : forecasts) {
                days.add(DayForecast.fromJson(day));
            }
        }
        return new Forecast(days);
    }

    public List<DayForecast> days() { return days; }
}
