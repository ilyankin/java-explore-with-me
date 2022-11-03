package ru.practicum.ewm.controllers.adminapi.compilation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.models.dtos.compilation.CompilationDto;
import ru.practicum.ewm.models.dtos.compilation.NewCompilationDto;
import ru.practicum.ewm.service.adminapi.compilation.CompilationServiceAdminImpl;

/**
 * The controller processes requests from compilation's admin API of the application.
 *
 * @author Izenkyt
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/compilations")
public class CompilationControllerAdmin {
    private final CompilationServiceAdminImpl compilationService;

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
