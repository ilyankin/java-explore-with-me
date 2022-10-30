package ru.practicum.ewm.event.model;


import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;
import ru.practicum.ewm.category.model.Category;
import ru.practicum.ewm.event.model.location.model.Location;
import ru.practicum.ewm.participation.model.ParticipationRequest;
import ru.practicum.ewm.user.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "events", uniqueConstraints = @UniqueConstraint(name = "uq_event_title", columnNames = "title"))
public final class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private long id;
    @Column(nullable = false, length = 120)
    private String title;
    @Column(nullable = false, length = 2000)
    private String annotation;
    @Column(nullable = false, length = 7000)
    private String description;
    @Column(name = "created_on", nullable = false)
    private LocalDateTime createdOn = LocalDateTime.now();
    @Column(name = "published_on")
    private LocalDateTime publishedOn;
    @Column(name = "date", nullable = false)
    private LocalDateTime eventDate;
    @ManyToOne
    @JoinColumn(name = "initiator_id", nullable = false)
    private User initiator;
    private boolean paid;
    @Column(name = "request_moderation")
    private boolean requestModeration;
    @Column(name = "participant_limit")
    private int participantLimit;
    @OneToMany
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "event_id")
    @Where(clause = "status = 'CONFIRMED'")
    private Set<ParticipationRequest> confirmedRequests = new HashSet<>();
    @Enumerated(EnumType.STRING)
    // @Type(type = "event_state_psql_enum")
    private EventState state = EventState.PENDING;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;
    @Transient
    private int views;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        return id == event.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
