package ru.practicum.ewm.dto.location;


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
    float lat;
    @Digits(integer = 3, fraction = 6)
    @Range(min = -180, max = 180)
    float lon;
}
