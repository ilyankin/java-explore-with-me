package ru.practicum.ewm.rest.client.privates.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.ewm.rest.client.BaseClient;

import java.util.Map;

@Service
public class ParticipationRequestClient extends BaseClient {
    private static final String API_PREFIX = "/users";

    @Autowired
    public ParticipationRequestClient(@Value("${ewm-server.url}") String url, RestTemplateBuilder builder) {
        super(builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(url + API_PREFIX))
                .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                .build()
        );
    }

    public ResponseEntity<Object> getParticipationRequest(Long userId) {
        return get("/" + userId + "requests");
    }

    public ResponseEntity<Object> createParticipationRequest(Long userId, Long eventId) {
        return post("/" + userId + "requests?eventId={eventId}", Map.of("eventId", eventId));
    }

    public ResponseEntity<Object> cancelParticipationRequest(Long userId, Long requestId) {
        return patch(String.format("/%s/requests/%s/cancel", userId, requestId));
    }
}
