package ru.practicum.ewm.event.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.ewm.BaseClient;
import ru.practicum.ewm.event.dto.AdminUpdateEventRequest;
import ru.practicum.ewm.event.dto.EventState;
import ru.practicum.ewm.util.UrlUtil;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;

@Service
public class EventClientAdmin extends BaseClient {
    private static final String API_PREFIX = "/admin/events";

    @Autowired
    public EventClientAdmin(@Value("${ewm-server.url}") String url, RestTemplateBuilder builder) {
        super(builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(url + API_PREFIX))
                .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                .build()
        );
    }

    public ResponseEntity<Object> getAllEvents(Set<Long> users, Set<String> states, Set<Long> categories,
                                               String rangeStart, String rangeEnd,
                                               Integer from, Integer size) {
        final Map<String, Object> parameters = Map.of(
                "rangeStart", URLEncoder.encode(rangeStart, StandardCharsets.UTF_8),
                "rangeEnd", URLEncoder.encode(rangeEnd, StandardCharsets.UTF_8),
                "from", from,
                "size", size);
        return get(String.format("?%s&%s&%s&rangeStart={rangeStart}&rangeEnd={rangeEnd}&from={from}&size={size}",
                UrlUtil.toArrayQueryParam(users, "users", id -> id > 0),
                UrlUtil.toArrayQueryParam(states, "states", EventState::isIn),
                UrlUtil.toArrayQueryParam(categories, "categories", id -> id > 0)
        ), parameters);
    }

    public ResponseEntity<Object> updateEvent(Long eventId, AdminUpdateEventRequest updateEventRequest) {
        return put("/" + eventId, updateEventRequest);
    }

    public ResponseEntity<Object> publishEvent(Long eventId) {
        return patch("/" + eventId + "/publish");
    }

    public ResponseEntity<Object> rejectEvent(Long eventId) {
        return patch("/" + eventId + "/reject");
    }
}
