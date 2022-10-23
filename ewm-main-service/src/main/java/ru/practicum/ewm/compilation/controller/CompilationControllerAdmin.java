package ru.practicum.ewm.compilation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.compilation.dto.CompilationDto;
import ru.practicum.ewm.compilation.dto.NewCompilationDto;
import ru.practicum.ewm.compilation.service.CompilationServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/compilations")
public class CompilationControllerAdmin {
    private final CompilationServiceImpl compilationService;

    @PostMapping
    public CompilationDto create(@RequestBody NewCompilationDto compilationDto) {
        return compilationService.createCompilation(compilationDto);
    }

    @DeleteMapping("/{compId}")
    public void delete(@PathVariable("compId") long compilationId) {
        compilationService.deleteCompilation(compilationId);
    }

    @PatchMapping("/{compId}/events/{eventId}")
    public void addEvent(@PathVariable("compId") long compilationId, @PathVariable long eventId) {
        compilationService.addEventToCompilation(compilationId, eventId);
    }

    @DeleteMapping("/{compId}/events/{eventId}")
    public void removeEvent(@PathVariable("compId") long compilationId, @PathVariable long eventId) {
        compilationService.removeEventFromCompilation(compilationId, eventId);
    }

    @PatchMapping("/{compId}/pin")
    public void pin(@PathVariable("compId") long compilationId) {
        compilationService.pinCompilation(compilationId);
    }

    @DeleteMapping("/{compId}/pin")
    public void unpin(@PathVariable("compId") long compilationId) {
        compilationService.unpinCompilation(compilationId);
    }
}
