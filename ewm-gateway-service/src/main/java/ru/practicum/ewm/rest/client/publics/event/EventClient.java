package ru.practicum.ewm.rest.client.publics.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.ewm.rest.client.BaseClient;
import ru.practicum.ewm.util.UrlUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class EventClient extends BaseClient {
    private static final String API_PREFIX = "/events";

    @Autowired
    public EventClient(@Value("${ewm-server.url}") String url, RestTemplateBuilder builder) {
        super(builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(url + API_PREFIX))
                .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                .build()
        );
    }

    public ResponseEntity<Object> getEvent(Long eventId) {
        return get("/" + eventId);
    }

    public ResponseEntity<Object> getAllEvents(String text, List<Long> categories, boolean paid,
                                               LocalDateTime rangeStart, LocalDateTime rangeEnd, boolean onlyAvailable,
                                               String sort, Integer from, Integer size,
                                               HttpServletRequest request) {
        final Map<String, Object> parameters = Map.of(
                "text", text,
                "paid", paid,
                "rangeStart", rangeStart,
                "rangeEnd", rangeEnd,
                "onlyAvailable", onlyAvailable,
                "sort", sort,
                "from", from,
                "size", size);
        return get("?" + UrlUtil.toArrayQueryParam(categories, "categories") +
                "&text={text}&paid={paid}&rangeStart={rangeStart}&rangeEnd={rangeEnd}&onlyAvailable={onlyAvailable}" +
                "&sort={sort}&from={from}&size={size}", parameters, request);
    }
}
