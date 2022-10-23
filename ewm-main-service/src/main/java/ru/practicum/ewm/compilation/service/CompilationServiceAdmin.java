package ru.practicum.ewm.compilation.service;

import ru.practicum.ewm.compilation.dto.CompilationDto;
import ru.practicum.ewm.compilation.dto.NewCompilationDto;

public interface CompilationServiceAdmin {
    CompilationDto createCompilation(NewCompilationDto compilationDto);

    void deleteCompilation(long compilationId);

    void addEventToCompilation(long compilationId, long eventId);

    void removeEventFromCompilation(long compilationId, long eventId);

    void pinCompilation(long compilationId);

    void unpinCompilation(long compilationId);
}
