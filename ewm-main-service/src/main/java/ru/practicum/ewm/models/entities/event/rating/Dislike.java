package ru.practicum.ewm.models.entities.event.rating;

import lombok.*;
import ru.practicum.ewm.models.entities.event.Event;
import ru.practicum.ewm.models.entities.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The entity representing an event dislike.
 *
 * @author Izenkyt
 */
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "dislikes")
public final class Dislike implements Serializable {
    @NonNull
    @EmbeddedId
    private PK dislikeId;
    @Column(name = "created_on")
    private LocalDateTime createdOn = LocalDateTime.now();

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class PK implements Serializable {
        @OneToOne(optional = false, fetch = FetchType.LAZY)
        @JoinColumn(name = "event_id")
        private Event event;
        @OneToOne(optional = false, fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        private User user;
    }
}
