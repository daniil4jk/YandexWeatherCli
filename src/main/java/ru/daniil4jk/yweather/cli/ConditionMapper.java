package ru.daniil4jk.yweather.cli;

import java.util.LinkedHashMap;
import java.util.Map;

public final class ConditionMapper {

    private static final Map<String, String> CONDITION_RU = new LinkedHashMap<>();
    private static final Map<String, String> WIND_DIR_RU = new LinkedHashMap<>();

    static {
        CONDITION_RU.put("clear", "Ясно");
        CONDITION_RU.put("partly-cloudy", "Малооблачно");
        CONDITION_RU.put("cloudy", "Облачно с прояснениями");
        CONDITION_RU.put("overcast", "Пасмурно");
        CONDITION_RU.put("drizzle", "Морось");
        CONDITION_RU.put("light-rain", "Небольшой дождь");
        CONDITION_RU.put("rain", "Дождь");
        CONDITION_RU.put("moderate-rain", "Умеренно сильный дождь");
        CONDITION_RU.put("heavy-rain", "Сильный дождь");
        CONDITION_RU.put("continuous-heavy-rain", "Длительный сильный дождь");
        CONDITION_RU.put("showers", "Ливень");
        CONDITION_RU.put("wet-snow", "Дождь со снегом");
        CONDITION_RU.put("light-snow", "Небольшой снег");
        CONDITION_RU.put("snow", "Снег");
        CONDITION_RU.put("snow-showers", "Снегопад");
        CONDITION_RU.put("hail", "Град");
        CONDITION_RU.put("thunderstorm", "Гроза");
        CONDITION_RU.put("thunderstorm-with-rain", "Гроза с дождём");
        CONDITION_RU.put("thunderstorm-with-hail", "Гроза с градом");

        WIND_DIR_RU.put("nw", "СЗ");
        WIND_DIR_RU.put("n", "С");
        WIND_DIR_RU.put("ne", "СВ");
        WIND_DIR_RU.put("e", "В");
        WIND_DIR_RU.put("se", "ЮВ");
        WIND_DIR_RU.put("s", "Ю");
        WIND_DIR_RU.put("sw", "ЮЗ");
        WIND_DIR_RU.put("w", "З");
    }

    public static String condition(String en) {
        return CONDITION_RU.getOrDefault(en, en);
    }

    public static String windDir(String dir) {
        return WIND_DIR_RU.getOrDefault(dir, dir);
    }

    private ConditionMapper() {}
}
