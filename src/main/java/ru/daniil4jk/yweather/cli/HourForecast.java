package ru.daniil4jk.yweather.cli;

import com.fasterxml.jackson.databind.JsonNode;

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
) {
    public static HourForecast fromJson(JsonNode node) {
        return new HourForecast(
            node.get("hour").asInt(),
            node.get("temp").asDouble(),
            node.get("feels_like").asDouble(),
            node.has("condition") ? node.get("condition").asText() : "",
            node.has("prec_strength") ? node.get("prec_strength").asDouble() : 0,
            node.has("prec_type") ? node.get("prec_type").asInt() : 0,
            node.has("wind_speed") ? node.get("wind_speed").asDouble() : 0,
            node.has("wind_gust") ? node.get("wind_gust").asDouble() : 0,
            node.has("humidity") ? node.get("humidity").asDouble() : 0,
            node.has("wind_dir") ? node.get("wind_dir").asText() : ""
        );
    }

    public String field(String name) {
        return switch (name) {
            case "hour"        -> String.valueOf(hour);
            case "temp"        -> formatTemp(temp);
            case "feels_like"  -> formatTemp(feelsLike);
            case "condition"   -> ConditionMapper.condition(condition);
            case "prec_strength" -> String.valueOf(precStrength);
            case "prec_type"   -> precTypeString(precType);
            case "wind_speed"  -> String.format("%.1f", windSpeed);
            case "wind_gust"   -> String.format("%.1f", windGust);
            case "humidity"    -> String.format("%.0f%%", humidity);
            case "wind_dir"    -> ConditionMapper.windDir(windDir);
            default -> "";
        };
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
}
