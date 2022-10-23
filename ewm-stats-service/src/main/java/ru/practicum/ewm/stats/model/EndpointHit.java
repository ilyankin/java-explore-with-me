package ru.practicum.ewm.stats.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "endpoint_hits",
        uniqueConstraints = @UniqueConstraint(name = "uq_endpoint_hit_uri_and_ip", columnNames = {"uri", "ip"}))
public class EndpointHit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String app;
    private String uri;
    private String ip;
    private LocalDateTime timestamp;
}