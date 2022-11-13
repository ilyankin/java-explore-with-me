package ru.practicum.ewm.controllers.publicapi.event.params;

import lombok.Value;

import java.time.LocalDateTime;

/**
 * This class contains a list of filtering parameters for that are used when searching for event using publish API.
 *
 * @author Izenkyt
 * @see ru.practicum.ewm.models.entities.event.Event
 */
@Value
public class PublicEventFilterParams {
    /**
     * Text contained in event's annotation or description
     */
    String text;
    /**
     * Categories in which the search will be conducted
     */
    Long[] categoryIds;
    /**
     * Paid or unpaid events
     */
    Boolean paid;
    /**
     * Date and time no earlier than which the event should occur
     */
    LocalDateTime rangeStart;
    /**
     * Date and time no later than which the event should occur
     */
    LocalDateTime rangeEnd;
    /**
     * Only events for which the participation limit has not been exhausted
     */
    boolean onlyAvailable;
    /**
     * Sort option: by event date or by number of views
     */
    SortType sortType;
    /**
     * Number of events that need to be skipped to form the current set events
     */
    int from;
    /**
     * Number of events in the set
     */
    int size;
}
