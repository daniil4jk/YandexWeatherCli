package ru.daniil4jk.yweather.model;

import java.util.List;

public final class DayForecast {
    private final String date;
    private final List<HourForecast> hours;

    public DayForecast(String date, List<HourForecast> hours) {
        this.date = date;
        this.hours = hours;
    }

    public String date() { return date; }
    public List<HourForecast> hours() { return hours; }
}
