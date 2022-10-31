package ru.practicum.ewm.controllers.adminapi.event.params;

import lombok.Value;
import ru.practicum.ewm.models.entities.event.EventState;

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
