package ru.daniil4jk.yweather.cli;

import java.util.ArrayList;
import java.util.List;

public final class WeatherFormatter {

    private final ConditionMapper conditionMapper;

    public WeatherFormatter(ConditionMapper conditionMapper) {
        this.conditionMapper = conditionMapper;
    }

    private static String formatTemp(double t) {
        return String.format("%+.0f", t);
    }

    private static String precTypeString(int t) {
        return switch (t) {
            case 0 -> "none";
            case 1 -> "rain";
            case 2 -> "snow";
            default -> "?";
        };
    }

    public void printCompact(Forecast forecast, CliArgs cfg) {
        HourFilter filter = cfg.hourFilter();
        List<String> fieldList = cfg.fields();

        System.out.println("@@WEATHER@@");
        System.out.println("date|" + String.join("|", fieldList));

        for (DayForecast day : forecast.days()) {
            for (HourForecast h : day.hours()) {
                if (!filter.matches(h.hour())) continue;

                List<String> row = new ArrayList<>();
                for (String f : fieldList) {
                    String trimmed = f.trim();
                    if (trimmed.isEmpty()) continue;
                    row.add(field(h, trimmed));
                }
                row.add(0, day.date());
                System.out.println(String.join("|", row));
            }
        }
    }

    private String field(HourForecast h, String name) {
        return switch (name) {
            case "hour" -> String.valueOf(h.hour());
            case "temp" -> formatTemp(h.temp());
            case "feels_like" -> formatTemp(h.feelsLike());
            case "condition" -> conditionMapper.condition(h.condition());
            case "prec_strength" -> String.valueOf(h.precStrength());
            case "prec_type" -> precTypeString(h.precType());
            case "wind_speed" -> String.format("%.1f", h.windSpeed());
            case "wind_gust" -> String.format("%.1f", h.windGust());
            case "humidity" -> String.format("%.0f%%", h.humidity());
            case "wind_dir" -> conditionMapper.windDir(h.windDir());
            default -> "";
        };
    }
}
