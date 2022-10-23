package ru.practicum.ewm.stats.model.dto;

import lombok.Value;

@Value
public class ViewStats {
    String app;
    String uri;
    long hits;
}
