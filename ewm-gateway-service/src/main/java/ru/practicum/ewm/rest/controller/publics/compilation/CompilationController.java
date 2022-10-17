package ru.practicum.ewm.rest.controller.publics.compilation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.rest.client.publics.compilation.CompilationClient;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/compilations")
public class CompilationController {
    private final CompilationClient compilationClient;

    @GetMapping("{compId}")
    public ResponseEntity<Object> get(@PathVariable("compId") @Positive Long compilationId) {
        return compilationClient.getCompilation(compilationId);
    }

    @GetMapping
    public ResponseEntity<Object> getAll(
            @RequestParam(required = false) Boolean pinned,
            @RequestParam(required = false, defaultValue = "0") @PositiveOrZero Integer from,
            @RequestParam(required = false, defaultValue = "10") @Positive Integer size) {
        return compilationClient.getAllCompilation(pinned, from, size);
    }
}
