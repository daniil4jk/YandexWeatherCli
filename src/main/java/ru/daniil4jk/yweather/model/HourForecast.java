package ru.daniil4jk.yweather.model;

public record HourForecast(
    int hour,
    double temp,
    double feelsLike,
    String condition,
    double precStrength,
    int precType,
    double windSpeed,
    double windGust,
    double humidity,
    String windDir
) {}
