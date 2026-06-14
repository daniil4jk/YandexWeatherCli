package ru.daniil4jk.yweather.cli;

import java.util.ArrayList;
import java.util.List;

public final class WeatherFormatter {

    public static void printCompact(Forecast forecast, CliArgs cfg) {
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
                    row.add(h.field(trimmed));
                }
                row.add(0, day.date());
                System.out.println(String.join("|", row));
            }
        }
    }
}
