package ru.daniil4jk.yweather.cli;

import java.util.List;

public record DayForecast(String date, List<HourForecast> hours) {
}
