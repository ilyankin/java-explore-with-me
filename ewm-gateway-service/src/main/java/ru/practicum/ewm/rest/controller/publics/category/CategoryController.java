package ru.practicum.ewm.rest.controller.publics.category;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.rest.client.publics.category.CategoryClient;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/categories")
public class CategoryController {
    private final CategoryClient categoryClient;

    @GetMapping("{catId}")
    public ResponseEntity<Object> get(@PathVariable("catId") @Positive Long categoryId) {
        return categoryClient.getCategory(categoryId);
    }

    @GetMapping
    public ResponseEntity<Object> getAll(
            @RequestParam(required = false, defaultValue = "0") @PositiveOrZero Integer from,
            @RequestParam(required = false, defaultValue = "10") @Positive Integer size) {
        return categoryClient.getAllCategories(from, size);
    }
}
