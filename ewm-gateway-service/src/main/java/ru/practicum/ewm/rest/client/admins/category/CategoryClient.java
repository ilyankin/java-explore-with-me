package ru.practicum.ewm.rest.client.admins.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.ewm.dto.category.CategoryDto;
import ru.practicum.ewm.dto.category.NewCategoryDto;
import ru.practicum.ewm.rest.client.BaseClient;

@Service
public class CategoryClient extends BaseClient {
    private static final String API_PREFIX = "/admin/categories";

    @Autowired
    public CategoryClient(@Value("${ewm-server.url}") String url, RestTemplateBuilder builder) {
        super(builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(url + API_PREFIX))
                .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                .build()
        );
    }

    public ResponseEntity<Object> createCategory(NewCategoryDto categoryDto) {
        return post("", categoryDto);
    }

    public ResponseEntity<Object> updateCategory(CategoryDto categoryDto) {
        return patch("", categoryDto);
    }

    public ResponseEntity<Object> deleteCategory(Long categoryId) {
        return delete("/" + categoryId);
    }
}
