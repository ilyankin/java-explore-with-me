package ru.practicum.ewm.category;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema = "main")
public class EventCategory {
    private long id;
    private String name;
}
