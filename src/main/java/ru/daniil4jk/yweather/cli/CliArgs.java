package ru.daniil4jk.yweather.cli;

import java.util.*;

public record CliArgs(
    String lat,
    String lon,
    int days,
    HourFilter hourFilter,
    List<String> fields
) {
    static final String DEFAULT_KEY = "6591d2b4-ab45-4a2e-ab81-0c8a21c41828";
    private static final String DEFAULT_FIELDS =
        "hour,temp,feels_like,condition,prec_strength,prec_type,wind_speed,wind_gust,humidity";

    public static CliArgs parse(String[] args) {
        String lat = null, lon = null, place = null;
        int days = 1;
        int singleHour = -1;
        String rangeStr = null;
        String fieldsStr = DEFAULT_FIELDS;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--lat" -> lat = args[++i];
                case "--lon" -> lon = args[++i];
                case "--place" -> place = args[++i];
                case "--days" -> days = Integer.parseInt(args[++i]);
                case "--hours" -> {
                    String h = args[++i];
                    if (h.contains("-")) {
                        rangeStr = h;
                    } else {
                        singleHour = Integer.parseInt(h);
                    }
                }
                case "--fields" -> fieldsStr = args[++i];
                default -> {
                    System.err.println("Unknown option: " + args[i]);
                    System.exit(1);
                }
            }
        }

        // Разрешаем --place вместо --lat/--lon
        if (place != null) {
            if (lat != null || lon != null) {
                System.err.println("--place нельзя использовать вместе с --lat/--lon");
                System.exit(1);
            }
            try {
                Place p = PlaceLoader.findByName(place);
                lat = p.lat();
                lon = p.lon();
            } catch (Exception e) {
                System.err.println("Ошибка загрузки " + e.getMessage());
                System.exit(1);
            }
        } else if (lat == null || lon == null) {
            System.err.println("Укажите --lat <lat> --lon <lon> или --place <название>");
            System.err.println("Использование: java -jar yweather.jar --lat <lat> --lon <lon> [options]");
            System.err.println("              java -jar yweather.jar --place <название> [options]");
            System.err.println("Options:");
            System.err.println("  --days <N>        Количество дней прогноза (по умолч. 1)");
            System.err.println("  --hours <N|->     Час или диапазон (3 или 12-22). По умолчанию все часы.");
            System.err.println("  --fields <list>   Поля для вывода");
            System.exit(1);
        }

        HourFilter filter;
        if (rangeStr != null) {
            var parts = rangeStr.split("-");
            filter = new HourFilter(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        } else if (singleHour >= 0) {
            filter = new HourFilter(singleHour, singleHour);
        } else {
            filter = new HourFilter(0, 23);
        }

        List<String> fields = Arrays.asList(fieldsStr.split(","));
        return new CliArgs(lat, lon, days, filter, fields);
    }
}
