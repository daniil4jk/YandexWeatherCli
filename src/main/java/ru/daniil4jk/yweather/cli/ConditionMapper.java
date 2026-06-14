package ru.daniil4jk.yweather.cli;

import java.util.LinkedHashMap;
import java.util.Map;

public final class ConditionMapper {

    private final Map<String, String> conditions;
    private final Map<String, String> windDirs;

    public ConditionMapper() {
        conditions = new LinkedHashMap<>();
        conditions.put("clear", "Ясно");
        conditions.put("partly-cloudy", "Малооблачно");
        conditions.put("cloudy", "Облачно с прояснениями");
        conditions.put("overcast", "Пасмурно");
        conditions.put("drizzle", "Морось");
        conditions.put("light-rain", "Небольшой дождь");
        conditions.put("rain", "Дождь");
        conditions.put("moderate-rain", "Умеренно сильный дождь");
        conditions.put("heavy-rain", "Сильный дождь");
        conditions.put("continuous-heavy-rain", "Длительный сильный дождь");
        conditions.put("showers", "Ливень");
        conditions.put("wet-snow", "Дождь со снегом");
        conditions.put("light-snow", "Небольшой снег");
        conditions.put("snow", "Снег");
        conditions.put("snow-showers", "Снегопад");
        conditions.put("hail", "Град");
        conditions.put("thunderstorm", "Гроза");
        conditions.put("thunderstorm-with-rain", "Гроза с дождём");
        conditions.put("thunderstorm-with-hail", "Гроза с градом");

        windDirs = new LinkedHashMap<>();
        windDirs.put("nw", "СЗ");
        windDirs.put("n", "С");
        windDirs.put("ne", "СВ");
        windDirs.put("e", "В");
        windDirs.put("se", "ЮВ");
        windDirs.put("s", "Ю");
        windDirs.put("sw", "ЮЗ");
        windDirs.put("w", "З");
    }

    public String condition(String en) {
        return conditions.getOrDefault(en, en);
    }

    public String windDir(String dir) {
        return windDirs.getOrDefault(dir, dir);
    }
}
