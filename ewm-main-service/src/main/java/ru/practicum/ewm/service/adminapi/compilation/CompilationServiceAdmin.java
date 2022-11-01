package ru.practicum.ewm.service.adminapi.compilation;

import ru.practicum.ewm.models.dtos.compilation.CompilationDto;
import ru.practicum.ewm.models.dtos.compilation.NewCompilationDto;

/**
 * The service contains methods for implementing the compilation's admin API application.
 *
 * @author Izenkyt
 */
public interface CompilationServiceAdmin {
    /**
     * Creates a new event compilation.
     *
     * @param compilationDto - dto for creating {@link ru.practicum.ewm.models.entities.compilation.Compilation}
     * @return {@link CompilationDto} - direct compilation dto
     */
    CompilationDto createCompilation(NewCompilationDto compilationDto);

    /**
     * Removes an event compilation.
     *
     * @param compilationId - compilation identifier
     */
    void deleteCompilation(long compilationId);

    /**
     * Adds an event to a compilation.
     *
     * @param compilationId - compilation identifier
     * @param eventId       - event identifier
     */
    void addEventToCompilation(long compilationId, long eventId);

    /**
     * Removes an event from a compilation.
     *
     * @param compilationId - compilation identifier
     * @param eventId       - event identifier
     */
    void removeEventFromCompilation(long compilationId, long eventId);

    /**
     * Set compilation's pinned status to {@code true}.
     *
     * @param compilationId - compilation identifier
     */
    void pinCompilation(long compilationId);

    /**
     * Set compilation's pinned status to {@code false}.
     *
     * @param compilationId - compilation identifier
     */
    void unpinCompilation(long compilationId);
}
