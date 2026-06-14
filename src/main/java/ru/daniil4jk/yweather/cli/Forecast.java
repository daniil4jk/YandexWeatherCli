package ru.daniil4jk.yweather.cli;

import java.util.List;

public record Forecast(List<DayForecast> days) {
}
