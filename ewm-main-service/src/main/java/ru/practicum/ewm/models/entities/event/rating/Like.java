package ru.practicum.ewm.models.entities.event.rating;


import lombok.*;
import ru.practicum.ewm.models.entities.event.Event;
import ru.practicum.ewm.models.entities.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The entity representing an event like.
 *
 * @author Izenkyt
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "likes")
@RequiredArgsConstructor
public final class Like implements Serializable {
    @NonNull
    @EmbeddedId
    private Like.PK likeId;
    @Column(name = "created_on")
    private LocalDateTime createdOn = LocalDateTime.now();

    /**
     * The entity representing a composite primary key of event like.
     *
     * @author Izenkyt
     */
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
