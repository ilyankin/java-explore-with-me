package ru.practicum.ewm.compilation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.compilation.dto.CompilationDto;
import ru.practicum.ewm.compilation.service.CompilationServiceImpl;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/compilations")
public class CompilationControllerPublic {
    private final CompilationServiceImpl compilationService;

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
