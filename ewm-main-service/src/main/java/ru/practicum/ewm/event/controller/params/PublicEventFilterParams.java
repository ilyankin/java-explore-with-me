package ru.practicum.ewm.event.controller.params;

import lombok.Value;

import java.time.LocalDateTime;


@Value
public class PublicEventFilterParams {
    String text;
    Long[] categoryIds;
    Boolean paid;
    LocalDateTime rangeStart;
    LocalDateTime rangeEnd;
    boolean onlyAvailable;
    SortType sortType;
    int from;
    int size;
}
