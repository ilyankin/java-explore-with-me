package ru.practicum.ewm.clients.privateapi.event.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.ewm.clients.BaseClient;

import java.util.Map;

@Service
public class EventRatingClientPrivate extends BaseClient {
    private static final String API_PREFIX = "/users";

    @Autowired
    public EventRatingClientPrivate(@Value("${ewm-server.url}") String url, RestTemplateBuilder builder) {
        super(builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(url + API_PREFIX))
                .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                .build()
        );
    }

    public ResponseEntity<Object> like(Long userId, Long eventId) {
        final Map<String, Object> parameters = Map.of("eventId", eventId);
        return patch("/" + userId + "/like?eventId={eventId}", parameters);
    }

    public ResponseEntity<Object> dislike(Long userId, Long eventId) {
        final Map<String, Object> parameters = Map.of("eventId", eventId);
        return patch("/" + userId + "/dislike?eventId={eventId}", parameters);
    }
}
