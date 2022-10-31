package ru.practicum.ewm.models.dtos.stats;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Value
@Builder
@Jacksonized
public class EndpointHitDto {
    @Builder.Default
    String app = "ewm-main-service";
    @NotBlank
    String uri;
    @NotBlank
    String ip;
    @Builder.Default
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime timestamp = LocalDateTime.now();
}
