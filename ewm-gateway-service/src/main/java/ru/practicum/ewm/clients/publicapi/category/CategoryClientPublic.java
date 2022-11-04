package ru.practicum.ewm.clients.publicapi.category;

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
public class CategoryClientPublic extends BaseClient {
    private static final String API_PREFIX = "/categories";

    @Autowired
    public CategoryClientPublic(@Value("${ewm-server.url}") String url, RestTemplateBuilder builder) {
        super(builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(url + API_PREFIX))
                .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                .build()
        );
    }

    public ResponseEntity<Object> getCategory(Long categoryId) {
        return get("/" + categoryId);
    }

    public ResponseEntity<Object> getAllCategories(Integer from, Integer size) {
        final Map<String, Object> parameters = Map.of("from", from, "size", size);
        return get("?from={from}&size={size}", parameters);
    }
}
