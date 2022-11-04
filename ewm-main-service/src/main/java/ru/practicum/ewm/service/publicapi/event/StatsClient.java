package ru.practicum.ewm.service.publicapi.event;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.ewm.models.dtos.stats.EndpointHitDto;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StatsClient {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final RestTemplate rest;


    @Autowired
    public StatsClient(@Value("${stat-service.url}") String serverUrl, RestTemplateBuilder builder) {
        this.rest = builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                .build();
    }

    private static ResponseEntity<Object> prepareGatewayResponse(ResponseEntity<Object> response) {
        if (response.getStatusCode().is2xxSuccessful()) return response;

        val responseBuilder = ResponseEntity.status(response.getStatusCode());

        if (response.hasBody()) return responseBuilder.body(response.getBody());

        return responseBuilder.build();
    }

    public void createStatistics(EndpointHitDto endpointHitDto) {
        makeAndSendRequest(HttpMethod.POST, "/hit", null, endpointHitDto);
    }

    public long getEventViews(long eventId) {
        val viewStats = (List<LinkedHashMap>) getStatistics(
                LocalDateTime.now().minusYears(10).format(formatter),
                LocalDateTime.now().plusYears(10).format(formatter),
                eventId, false).getBody();

        if (viewStats == null || viewStats.isEmpty()) return 0;
        val viewStat = viewStats.get(0);
        if (viewStat == null) return 0;
        return (int) viewStat.get("hits");
    }

    private ResponseEntity<Object> getStatistics(String start, String end, long eventId, Boolean unique) {
        Map<String, Object> parameters = Map.of(
                "start", URLEncoder.encode(start, StandardCharsets.UTF_8),
                "end", URLEncoder.encode(end, StandardCharsets.UTF_8),
                "uris", "/events/" + eventId,
                "unique", unique);
        return makeAndSendRequest(HttpMethod.GET,
                "/stats?start={start}&end={end}&uris={uris}&unique={unique}", parameters, null);
    }

    private <T> ResponseEntity<Object> makeAndSendRequest(HttpMethod method, String path,
                                                          @Nullable Map<String, Object> parameters, @Nullable T body) {
        val requestEntity = new HttpEntity<>(body, defaultHeaders());

        ResponseEntity<Object> response;
        try {
            if (parameters != null) {
                response = rest.exchange(path, method, requestEntity, Object.class, parameters);
            } else {
                response = rest.exchange(path, method, requestEntity, Object.class);
            }
        } catch (HttpStatusCodeException e) {
            return ResponseEntity
                    .status(e.getStatusCode())
                    .body(e.getResponseBodyAsByteArray());
        }
        return prepareGatewayResponse(response);
    }

    private HttpHeaders defaultHeaders() {
        val headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return headers;
    }
}
