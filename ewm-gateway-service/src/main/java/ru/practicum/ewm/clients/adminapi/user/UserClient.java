package ru.practicum.ewm.clients.adminapi.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.ewm.clients.BaseClient;
import ru.practicum.ewm.dtos.user.NewUserRequest;
import ru.practicum.ewm.util.UrlUtil;

import java.util.Map;
import java.util.Set;

@Service
public class UserClient extends BaseClient {
    private static final String API_PREFIX = "/admin/users";

    @Autowired
    public UserClient(@Value("${ewm-server.url}") String url, RestTemplateBuilder builder) {
        super(builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(url + API_PREFIX))
                .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                .build()
        );
    }

    public ResponseEntity<Object> getUsers(Set<Long> ids, Integer from, Integer size) {
        final Map<String, Object> parameters = Map.of("from", from, "size", size);
        return get("?" + UrlUtil.toArrayQueryParam(ids, "ids", id -> id > 0)
                + "&from={from}&size={size}", parameters, null);
    }

    public ResponseEntity<Object> createUser(NewUserRequest userRequest) {
        return post("", userRequest);
    }

    public ResponseEntity<Object> deleteUser(Long userId) {
        return delete("/" + userId);
    }
}
