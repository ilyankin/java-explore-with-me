package ru.practicum.ewm.event.controller.params;

import lombok.Value;

@Value
public class RequestMetaData {
    String remoteAddress;
    String requestURI;
}
