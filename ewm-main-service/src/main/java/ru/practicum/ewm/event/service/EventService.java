package ru.practicum.ewm.event.service;

import ru.practicum.ewm.participation.service.ParticipationServicePrivate;

public interface EventService extends EventServicePublic, EventServicePrivate, EventServiceAdmin,
        ParticipationServicePrivate {
}
