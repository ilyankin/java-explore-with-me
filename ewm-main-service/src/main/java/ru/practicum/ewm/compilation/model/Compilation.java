package ru.practicum.ewm.compilation.model;


import lombok.*;
import ru.practicum.ewm.event.model.Event;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "compilations", uniqueConstraints = @UniqueConstraint(name = "uq_compilation_id", columnNames = "title"))
public class Compilation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "compilation_id")
    private long id;
    private boolean pinned;
    @Column(nullable = false, length = 256)
    private String title;
    @ManyToMany
    @JoinTable(name = "event_compilation",
            joinColumns = @JoinColumn(name = "compilation_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private Collection<Event> events;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Compilation that = (Compilation) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
