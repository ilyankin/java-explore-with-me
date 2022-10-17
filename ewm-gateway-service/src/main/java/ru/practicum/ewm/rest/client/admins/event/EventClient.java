package ru.practicum.ewm.rest.client.admins.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.ewm.dto.request.AdminUpdateEventRequest;
import ru.practicum.ewm.rest.client.BaseClient;
import ru.practicum.ewm.util.UrlUtil;

import java.util.List;
import java.util.Map;

@Service
public class EventClient extends BaseClient {
    private static final String API_PREFIX = "/admin/events";

    @Autowired
    public EventClient(@Value("${ewm-server.url}") String url, RestTemplateBuilder builder) {
        super(builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(url + API_PREFIX))
                .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                .build()
        );
    }

    public ResponseEntity<Object> getAllEvents(List<Long> users, List<String> states, List<Long> categories,
                                               String rangeStart, String rangeEnd,
                                               Integer from, Integer size) {
        final Map<String, Object> parameters = Map.of(
                "rangeStart", rangeStart,
                "rangeEnd", rangeEnd,
                "from", from,
                "size", size);
        return get(String.format("?%s&%s&%s&rangeStart={rangeStart}&rangeEnd={rangeEnd}&from={from}&size={size}",
                UrlUtil.toArrayQueryParam(users, "users"),
                UrlUtil.toArrayQueryParam(states, "states"),
                UrlUtil.toArrayQueryParam(categories, "categories")
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
