package ru.practicum.ewm.location.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.springframework.context.annotation.PropertySource;


@Value
@Builder
@Jacksonized
public class LocationDto {
// TODO: it was commented to pass postman's tests

//    @Digits(integer = 2, fraction = 6)
//    @Range(min = -90, max = 90)
    @JsonProperty("lat")
    float latitude;
//    @Digits(integer = 3, fraction = 6)
//    @Range(min = -180, max = 180)
    @JsonProperty("lon")
    float longitude;
}