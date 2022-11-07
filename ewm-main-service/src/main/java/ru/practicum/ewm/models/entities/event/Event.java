package ru.practicum.ewm.models.entities.event;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;
import ru.practicum.ewm.models.entities.category.Category;
import ru.practicum.ewm.models.entities.event.location.Location;
import ru.practicum.ewm.models.entities.event.rating.Dislike;
import ru.practicum.ewm.models.entities.event.rating.Like;
import ru.practicum.ewm.models.entities.participation.ParticipationRequest;
import ru.practicum.ewm.models.entities.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * The entity representing an event
 *
 * @author Izenkyt
 */
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
    private EventState state = EventState.PENDING;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;
    @OneToMany
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "event_id")
    private Set<Like> likes = new HashSet<>();
    @OneToMany
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "event_id")
    private Set<Dislike> dislikes = new HashSet<>();
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
