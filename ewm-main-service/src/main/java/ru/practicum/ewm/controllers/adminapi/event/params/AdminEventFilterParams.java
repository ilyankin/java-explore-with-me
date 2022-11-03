package ru.practicum.ewm.controllers.adminapi.event.params;

import lombok.Value;
import ru.practicum.ewm.models.entities.event.EventState;

import java.time.LocalDateTime;

/**
 * This class contains a list of filtering parameters for that are used when searching for event using admin API.
 *
 * @author Izenkyt
 */
@Value
public class AdminEventFilterParams {
    /**
     * User ids whose events are to be found
     */
    Long[] userIds;
    /**
     * States in which the searched events are located
     */
    EventState[] states;
    /**
     * Categories in which the search will be conducted
     */
    Long[] categoryIds;
    /**
     * Date and time no earlier than which the event should occur
     */
    LocalDateTime rangeStart;
    /**
     * Date and time no later than which the event should occur
     */
    LocalDateTime rangeEnd;
    /**
     * Number of events that need to be skipped to form the current set events
     */
    int from;
    /**
     * Number of events in the set
     */
    int size;
}
