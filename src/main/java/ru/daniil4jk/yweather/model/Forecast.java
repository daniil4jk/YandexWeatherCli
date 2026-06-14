package ru.daniil4jk.yweather.model;

import java.util.List;

public final class Forecast {
    private final List<DayForecast> days;

    public Forecast(List<DayForecast> days) {
        this.days = days;
    }

    public List<DayForecast> days() { return days; }
}
