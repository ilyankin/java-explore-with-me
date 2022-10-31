package ru.practicum.ewm.controllers.publicapi.event.params;

import lombok.Value;

@Value
public class RequestMetaData {
    String remoteAddress;
    String requestURI;
}
