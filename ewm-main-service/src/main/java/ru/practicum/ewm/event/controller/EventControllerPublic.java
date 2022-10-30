package ru.practicum.ewm.event.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.event.controller.params.PublicEventFilterParams;
import ru.practicum.ewm.event.controller.params.RequestMetaData;
import ru.practicum.ewm.event.controller.params.SortType;
import ru.practicum.ewm.event.model.dto.EventFullDto;
import ru.practicum.ewm.event.model.dto.EventShortDto;
import ru.practicum.ewm.event.service.EventServicePublic;
import ru.practicum.ewm.util.UrlUtil;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/events")
public class EventControllerPublic {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final EventServicePublic eventService;


    @GetMapping
    public Collection<EventShortDto> getAll(@RequestParam(required = false) String text,
                                            @RequestParam(required = false) Long[] categories,
                                            @RequestParam(required = false) Boolean paid,
                                            @RequestParam(required = false) String rangeStart,
                                            @RequestParam(required = false) String rangeEnd,
                                            @RequestParam boolean onlyAvailable,
                                            @RequestParam(name = "sort") SortType sortType,
                                            @RequestParam int from,
                                            @RequestParam int size,
                                            @RequestHeader("X-Request-URI") String requestURI,
                                            @RequestHeader("X-Remote-Address") String remoteAddress) {
        val dateTimes = UrlUtil.decodeLocalDateTime(formatter, rangeStart, rangeEnd);
        val rangeStartDateTime = dateTimes.get(0);
        val rangeEndDateTime = dateTimes.get(1);
        return eventService.getAllEventsByPublicParams(
                new PublicEventFilterParams(URLDecoder.decode(text, StandardCharsets.UTF_8), categories, paid,
                        rangeStartDateTime, rangeEndDateTime,
                        onlyAvailable, sortType, from, size),
                new RequestMetaData(remoteAddress, requestURI));
    }

    @GetMapping("{id}")
    public EventFullDto get(@PathVariable("id") long eventId,
                            @RequestHeader("X-Request-URI") String requestURI,
                            @RequestHeader("X-Remote-Address") String remoteAddress) {
        return eventService.getEvent(eventId, new RequestMetaData(remoteAddress, requestURI));
    }

}
