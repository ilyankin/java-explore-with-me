package ru.practicum.ewm.dtos.event.location;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Digits;


@Value
@Builder
@Jacksonized
public class LocationDto {
    @Digits(integer = 2, fraction = 6)
    @Range(min = -90, max = 90)
    @JsonProperty("lat")
    float latitude;
    @Digits(integer = 3, fraction = 6)
    @Range(min = -180, max = 180)
    @JsonProperty("lon")
    float longitude;
}