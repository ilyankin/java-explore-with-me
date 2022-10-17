package ru.practicum.ewm.rest.client.privates.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.ewm.dto.event.NewEventDto;
import ru.practicum.ewm.dto.request.UpdateEventRequest;
import ru.practicum.ewm.rest.client.BaseClient;

import java.util.Map;

@Service
public class EventClient extends BaseClient {
    private static final String API_PREFIX = "/users";

    @Autowired
    public EventClient(@Value("${ewm-server.url}") String url, RestTemplateBuilder builder) {
        super(builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(url + API_PREFIX))
                .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                .build()
        );
    }

    public ResponseEntity<Object> getEvents(Long userId, Integer from, Integer size) {
        final Map<String, Object> parameters = Map.of("from", from, "size", size);
        return get("/" + userId + "events?from={from}&size={size}", parameters);
    }

    public ResponseEntity<Object> getEvent(Long userId, Long eventId) {
        return get("/" + userId + "events/" + eventId);
    }

    public ResponseEntity<Object> updateEvent(Long userId, UpdateEventRequest eventRequest) {
        return patch("/" + userId + "events", eventRequest);
    }

    public ResponseEntity<Object> createEvent(Long userId, NewEventDto event) {
        return post("/" + userId + "events", event);
    }

    public ResponseEntity<Object> cancelEvent(Long userId, Long eventId) {
        return patch("/" + userId + "events/" + eventId);
    }

    public ResponseEntity<Object> getEventRequests(Long userId, Long eventId) {
        return get(String.format("/%s/events/%s/requests", userId, eventId));
    }

    public ResponseEntity<Object> confirmEventRequest(Long userId, Long eventId, Long requestId) {
        return patch(String.format("/%s/events/%s/requests/%s/confirm", userId, eventId, requestId));
    }

    public ResponseEntity<Object> rejectEventRequest(Long userId, Long eventId, Long requestId) {
        return patch(String.format("/%s/events/%s/requests/%s/reject", userId, eventId, requestId));
    }
}
