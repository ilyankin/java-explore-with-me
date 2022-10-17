package ru.practicum.ewm.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import ru.practicum.ewm.constraint.EnumValue;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@Value
@Builder
@Jacksonized
public class ParticipationRequestDto {
    @PositiveOrZero
    long id;
    @Positive
    @JsonProperty("event")
    long eventId;
    @Positive
    @JsonProperty("requester")
    long requesterId;
    LocalDateTime created;
    @EnumValue(enumClass = PRStatus.class)
    PRStatus status;
}
