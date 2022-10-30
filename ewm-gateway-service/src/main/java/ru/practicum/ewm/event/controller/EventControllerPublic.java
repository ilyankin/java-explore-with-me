package ru.practicum.ewm.event.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.constraint.EnumValue;
import ru.practicum.ewm.event.client.EventClientPublic;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Set;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/events")
public class EventControllerPublic {
    private final EventClientPublic eventClientPublic;

    @GetMapping("{id}")
    public ResponseEntity<Object> get(@PathVariable("id") @Positive Long eventId, HttpServletRequest request) {
        return eventClientPublic.getEvent(eventId, request);
    }

    @GetMapping
    public ResponseEntity<Object> getAll(
            @RequestParam(required = false) @NotBlank String text,
            @RequestParam(required = false) @NotEmpty Set<Long> categories,
            @RequestParam(required = false) Boolean paid,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") String rangeStart,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") String rangeEnd,
            @RequestParam(required = false, defaultValue = "false") Boolean onlyAvailable,
            @RequestParam(defaultValue = "EVENT_DATE") @EnumValue(enumClass = SortType.class) String sort,
            @RequestParam(required = false, defaultValue = "0") @PositiveOrZero Integer from,
            @RequestParam(required = false, defaultValue = "10") @Positive Integer size,
            HttpServletRequest request) {
        return eventClientPublic.getAllEvents(text, categories, paid,
                rangeStart, rangeEnd, onlyAvailable, sort,
                from, size, request);
    }

    private enum SortType {
        EVENT_DATE,
        VIEWS,
    }
}
