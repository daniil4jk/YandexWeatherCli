package ru.daniil4jk.yweather.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record Place(
    List<String> names,
    String lat,
    String lon
) {}
