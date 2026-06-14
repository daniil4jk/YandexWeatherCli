package ru.daniil4jk.yweather.cli;

public record HourFilter(int start, int end) {
    public boolean matches(int hour) {
        return hour >= start && hour <= end;
    }
}
