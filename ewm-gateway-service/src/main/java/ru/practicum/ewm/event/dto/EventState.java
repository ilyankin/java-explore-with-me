package ru.practicum.ewm.event.dto;

import lombok.val;

public enum EventState {
    PENDING,
    PUBLISHED,
    CANCELED;

    public static boolean isIn(String state) {
        if (state == null || state.isBlank()) return false;
        for (val eventState : EventState.values()) {
            if (state.equals(eventState.name())) return true;
        }
        return false;
    }
}
