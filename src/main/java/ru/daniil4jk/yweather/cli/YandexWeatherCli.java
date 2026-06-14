package ru.daniil4jk.yweather.cli;

/**
 * Yandex Weather API v2 CLI — компактный weather-клиент для AI-агентов.
 *
 * Использование:
 *   java -jar yweather.jar --lat 59.9 --lon 60.6
 *   java -jar yweather.jar --place Краснотурьинск
 *   java -jar yweather.jar --place Severouralsk --hours 12-22
 *   java -jar yweather.jar --lat 59.9 --lon 60.6 --hours 3 --fields hour,temp
 */
public class YandexWeatherCli {

    public static void main(String[] args) throws Exception {
        CliArgs cfg = CliArgs.parse(args);
        Forecast forecast = WeatherClient.fetch(cfg);
        WeatherFormatter.printCompact(forecast, cfg);
    }
}
