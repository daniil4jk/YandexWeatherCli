package ru.daniil4jk.yweather.cli;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.List;

public final class DayForecast {
    private final String date;
    private final List<HourForecast> hours;

    public DayForecast(String date, List<HourForecast> hours) {
        this.date = date;
        this.hours = hours;
    }

    public static DayForecast fromJson(JsonNode node) {
        String date = node.get("date").asText();
        JsonNode hoursArr = node.get("hours");
        List<HourForecast> hours = new ArrayList<>();
        if (hoursArr != null && hoursArr.isArray()) {
            for (JsonNode h : hoursArr) {
                hours.add(HourForecast.fromJson(h));
            }
        }
        return new DayForecast(date, hours);
    }

    public String date() { return date; }
    public List<HourForecast> hours() { return hours; }
}
