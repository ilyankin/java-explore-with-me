package ru.practicum.ewm.user.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users", schema = "main")
public class User {
    private long id;
    private String email;
    private String name;
}
