package ru.practicum.ewm.rest.controller.publics.event;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.constraint.EnumValue;
import ru.practicum.ewm.rest.client.publics.event.EventClient;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/events")
public class EventController {
    private final EventClient eventClient;

    @GetMapping("{id}")
    public ResponseEntity<Object> get(@PathVariable("id") @Positive Long eventId) {
        return eventClient.getEvent(eventId);
    }

    @GetMapping
    public ResponseEntity<Object> getAll(
            @RequestParam(required = false) @NotBlank String text,
            @RequestParam(required = false) @NotEmpty List<@Positive Long> categories,
            @RequestParam(required = false) Boolean paid,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeStart,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeEnd,
            @RequestParam(required = false, defaultValue = "false") Boolean onlyAvailable,
            @RequestParam @EnumValue(enumClass = SortType.class) String sort,
            @RequestParam(required = false, defaultValue = "0") @PositiveOrZero Integer from,
            @RequestParam(required = false, defaultValue = "10") @Positive Integer size,
            HttpServletRequest request) {
        return eventClient.getAllEvents(text, categories, paid,
                rangeStart, rangeEnd, onlyAvailable, sort,
                from, size, request);
    }

    private enum SortType {
        EVENT_DATE,
        VIEWS,
    }
}
