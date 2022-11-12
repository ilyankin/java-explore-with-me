package ru.practicum.ewm.clients.publicapi.event;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.ewm.clients.BaseClient;
import ru.practicum.ewm.util.UrlUtil;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Set;

@Service
public class EventClientPublic extends BaseClient {
    private static final String API_PREFIX = "/events";

    @Autowired
    public EventClientPublic(@Value("${ewm-server.url}") String url, RestTemplateBuilder builder) {
        super(builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(url + API_PREFIX))
                .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                .build()
        );
    }

    public ResponseEntity<Object> getEvent(Long eventId, HttpServletRequest request) {
        val headers = new HashMap<String, String>();
        headers.put("X-Request-URI", request.getRequestURI());
        headers.put("X-Remote-Address", request.getRemoteAddr());
        return get("/" + eventId, null, headers);
    }

    public ResponseEntity<Object> getAllEvents(String text, Set<Long> categories, boolean paid,
                                               String rangeStart, String rangeEnd, boolean onlyAvailable,
                                               String sort, Integer from, Integer size,
                                               HttpServletRequest request) {
        val parameters = new HashMap<String, Object>();
        parameters.put("text", URLEncoder.encode(text, StandardCharsets.UTF_8));
        parameters.put("paid", paid);
        parameters.put("rangeStart", rangeStart == null ? "" : URLEncoder.encode(rangeStart, StandardCharsets.UTF_8));
        parameters.put("rangeEnd", rangeEnd == null ? "" : URLEncoder.encode(rangeEnd, StandardCharsets.UTF_8));
        parameters.put("onlyAvailable", onlyAvailable);
        parameters.put("sort", sort);
        parameters.put("from", from);
        parameters.put("size", size);
        val headers = new HashMap<String, String>();
        headers.put("X-Request-URI", request.getRequestURI());
        headers.put("X-Remote-Address", request.getRemoteAddr());
        return get("?text={text}&" + UrlUtil.toArrayQueryParam(categories, "categories", id -> id > 0) +
                "paid={paid}&rangeStart={rangeStart}&rangeEnd={rangeEnd}&onlyAvailable={onlyAvailable}" +
                "&sort={sort}&from={from}&size={size}", parameters, headers);
    }
}
