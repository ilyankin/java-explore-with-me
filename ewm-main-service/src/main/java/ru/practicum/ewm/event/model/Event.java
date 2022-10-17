package ru.practicum.ewm.event.model;


import ru.practicum.ewm.category.EventCategory;
import ru.practicum.ewm.location.Location;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "events", schema = "main")
public class Event {
    private long id;
    private String title;
    private String description;
    private LocalDateTime createdOn;
    private LocalDateTime publishedOn;
    private EventCategory eventCategory;
    private EventState eventState;
    private Location location;
    private boolean paid;
    private
}
