package ru.practicum.ewm.participation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import ru.practicum.ewm.participation.model.PRStatus;

import java.time.LocalDateTime;

@Value
@Builder
@Jacksonized
public class ParticipationRequestDto {
    long id;
    long event;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime created;
    long requester;
    PRStatus status;
}
