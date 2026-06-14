package ru.daniil4jk.yweather.cli;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Модель города из places.json.
 */
public record Place(
    List<String> names,
    String lat,
    String lon
) {}
