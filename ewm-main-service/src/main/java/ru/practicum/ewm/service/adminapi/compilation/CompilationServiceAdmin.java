package ru.practicum.ewm.service.adminapi.compilation;

import ru.practicum.ewm.models.dtos.compilation.CompilationDto;
import ru.practicum.ewm.models.dtos.compilation.NewCompilationDto;

public interface CompilationServiceAdmin {
    CompilationDto createCompilation(NewCompilationDto compilationDto);

    void deleteCompilation(long compilationId);

    void addEventToCompilation(long compilationId, long eventId);

    void removeEventFromCompilation(long compilationId, long eventId);

    void pinCompilation(long compilationId);

    void unpinCompilation(long compilationId);
}
