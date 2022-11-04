package ru.practicum.ewm.controllers.publicapi.compilation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.models.dtos.compilation.CompilationDto;
import ru.practicum.ewm.service.publicapi.compilation.CompilationServicePublic;

import java.util.Collection;

/**
 * The controller processes requests from compilation's public API of the application.
 *
 * @author Izenkyt
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/compilations")
public class CompilationControllerPublic {
    private final CompilationServicePublic compilationService;

    @GetMapping
    public Collection<CompilationDto> getAll(@RequestParam(required = false) Boolean pinned,
                                             @RequestParam int from, @RequestParam int size) {
        return compilationService.getAllCompilations(pinned, from, size);
    }

    @GetMapping("{compId}")
    public CompilationDto get(@PathVariable("compId") long compilationId) {
        return compilationService.getCompilation(compilationId);
    }
}
