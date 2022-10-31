package ru.practicum.ewm.service.publicapi.compilation;

import ru.practicum.ewm.models.dtos.compilation.CompilationDto;

import java.util.Collection;

public interface CompilationServicePublic {
    Collection<CompilationDto> getAllCompilations(Boolean pinned, int from, int size);

    CompilationDto getCompilation(long compilationId);
}
