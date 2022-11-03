package ru.practicum.ewm.service.publicapi.compilation;

import ru.practicum.ewm.models.dtos.compilation.CompilationDto;

import java.util.Collection;

/**
 * The service contains methods for implementing the compilation's public API application.
 *
 * @author Izenkyt
 */
public interface CompilationServicePublic {
    /**
     * Gets a collection of event compilation.
     *
     * @param pinned - filter {@code true} - pinned compilations, @{@code false} - unpinned compilations
     * @param from   - number of compilations that need to be skipped to form the returned collection of compilations
     * @param size   - number of compilations in the returned collection of compilations
     * @return collection of {@link CompilationDto} - direct compilation dto
     */
    Collection<CompilationDto> getAllCompilations(Boolean pinned, int from, int size);


    /**
     * Gets a event compilation.
     *
     * @param compilationId - compilation identifier
     * @return {@link CompilationDto} - direct compilation dto
     */
    CompilationDto getCompilation(long compilationId);
}
