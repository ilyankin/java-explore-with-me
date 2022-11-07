package ru.practicum.ewm.models.entities.event.rating;


import lombok.*;
import ru.practicum.ewm.models.entities.event.Event;
import ru.practicum.ewm.models.entities.user.User;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The entity representing an event like.
 *
 * @author Izenkyt
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "likes")
public final class Like {
    @EmbeddedId
    private Like.PK likeId;

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
