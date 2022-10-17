package ru.practicum.ewm.location;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "locations", schema = "main")
public class Location {
    private float latitude;
    private float longitude;
}
