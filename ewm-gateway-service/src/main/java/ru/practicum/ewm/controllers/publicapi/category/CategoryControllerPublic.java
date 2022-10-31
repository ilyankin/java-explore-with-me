package ru.practicum.ewm.controllers.publicapi.category;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.clients.publicapi.category.CategoryClientPublic;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/categories")
public class CategoryControllerPublic {
    private final CategoryClientPublic categoryClientPublic;

    @GetMapping("{catId}")
    public ResponseEntity<Object> get(@PathVariable("catId") @Positive Long categoryId) {
        return categoryClientPublic.getCategory(categoryId);
    }

    @GetMapping
    public ResponseEntity<Object> getAll(
            @RequestParam(required = false, defaultValue = "0") @PositiveOrZero Integer from,
            @RequestParam(required = false, defaultValue = "10") @Positive Integer size) {
        return categoryClientPublic.getAllCategories(from, size);
    }
}
