package ru.practicum.ewm.compilation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.compilation.client.CompilationClientAdmin;
import ru.practicum.ewm.compilation.dto.NewCompilationDto;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/compilations")
public class CompilationControllerAdmin {
    private final CompilationClientAdmin compilationClient;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid NewCompilationDto compilationDto) {
        return compilationClient.createCompilation(compilationDto);
    }

    @DeleteMapping("{compId}")
    public ResponseEntity<Object> delete(@PathVariable("compId") @Positive Long compilationId) {
        return compilationClient.deleteCompilation(compilationId);
    }

    @DeleteMapping("{compId}/events/{eventId}")
    public ResponseEntity<Object> removeEvent(@PathVariable("compId") @Positive Long compilationId,
                                              @PathVariable("eventId") @Positive Long eventId) {
        return compilationClient.removeEventFromCompilation(compilationId, eventId);
    }

    @PatchMapping("{compId}/events/{eventId}")
    public ResponseEntity<Object> addEvent(@PathVariable("compId") @Positive Long compilationId,
                                           @PathVariable("eventId") @Positive Long eventId) {
        return compilationClient.addEventToCompilation(compilationId, eventId);
    }

    @PatchMapping("{compId}/pin")
    public ResponseEntity<Object> pin(@PathVariable("compId") @Positive Long compilationId) {
        return compilationClient.pinCompilation(compilationId);
    }

    @DeleteMapping("{compId}/pin")
    public ResponseEntity<Object> unpin(@PathVariable("compId") @Positive Long compilationId) {
        return compilationClient.unpinCompilation(compilationId);
    }
}
