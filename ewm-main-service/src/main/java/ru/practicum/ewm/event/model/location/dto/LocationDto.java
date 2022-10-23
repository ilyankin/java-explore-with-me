package ru.practicum.ewm.event.model.location.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class LocationDto {
    @JsonProperty("lat")
    float latitude;
    @JsonProperty("lon")
    float longitude;
}
