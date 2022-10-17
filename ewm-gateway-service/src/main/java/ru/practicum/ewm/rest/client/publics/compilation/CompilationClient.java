package ru.practicum.ewm.rest.client.publics.compilation;

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
public class CompilationClient extends BaseClient {
    private static final String API_PREFIX = "/compilation";

    @Autowired
    public CompilationClient(@Value("${ewm-server.url}") String url, RestTemplateBuilder builder) {
        super(builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(url + API_PREFIX))
                .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                .build()
        );
    }

    public ResponseEntity<Object> getCompilation(Long compilationId) {
        return get("/" + compilationId);
    }

    public ResponseEntity<Object> getAllCompilation(Boolean pinned, Integer from, Integer size) {
        final Map<String, Object> parameters = Map.of(
                "pinned", pinned,
                "from", from,
                "size", size);
        return get("?pinned={pinned}&from={from}&size={size}", parameters);
    }
}
