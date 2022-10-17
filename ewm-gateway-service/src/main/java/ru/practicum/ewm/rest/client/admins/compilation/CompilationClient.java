package ru.practicum.ewm.rest.client.admins.compilation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.ewm.dto.compilation.NewCompilationDto;
import ru.practicum.ewm.rest.client.BaseClient;

@Service
public class CompilationClient extends BaseClient {
    private static final String API_PREFIX = "/admin/compilations";

    @Autowired
    public CompilationClient(@Value("${ewm-server.url}") String url, RestTemplateBuilder builder) {
        super(builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(url + API_PREFIX))
                .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                .build()
        );
    }

    public ResponseEntity<Object> createCompilation(NewCompilationDto compilationDto) {
        return post("", compilationDto);
    }

    public ResponseEntity<Object> deleteCompilation(Long compilationId) {
        return delete("/" + compilationId);
    }

    public ResponseEntity<Object> removeEventFromCompilation(Long compilationId, Long eventId) {
        return delete("/" + compilationId + "/event/" + eventId);
    }

    public ResponseEntity<Object> addEventToCompilation(Long compilationId, Long eventId) {
        return patch("/" + compilationId + "/event/" + eventId);
    }

    public ResponseEntity<Object> pinCompilation(Long compilationId) {
        return patch("/" + compilationId + "/pin");
    }

    public ResponseEntity<Object> unpinCompilation(Long compilationId) {
        return delete("/" + compilationId + "/pin");
    }
}
