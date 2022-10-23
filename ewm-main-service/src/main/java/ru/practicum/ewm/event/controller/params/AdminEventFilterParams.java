package ru.practicum.ewm.event.controller.params;

import lombok.Value;
import ru.practicum.ewm.event.model.EventState;

import java.time.LocalDateTime;

@Value
public class AdminEventFilterParams {
    Long[] userIds;
    EventState[] states;
    Long[] categoryIds;
    LocalDateTime rangeStart;
    LocalDateTime rangeEnd;
    int from;
    int size;
}
