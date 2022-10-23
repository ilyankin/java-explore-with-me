package ru.practicum.ewm.compilation.service;

import ru.practicum.ewm.compilation.dto.CompilationDto;

import java.util.Collection;

public interface CompilationServicePublic {
    Collection<CompilationDto> getAllCompilations(Boolean pinned, int from, int size);

    CompilationDto getCompilation(long compilationId);
}
