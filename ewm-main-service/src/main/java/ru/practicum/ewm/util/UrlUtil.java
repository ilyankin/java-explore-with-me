package ru.practicum.ewm.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.val;

import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UrlUtil {
    public static List<LocalDateTime> decodeLocalDateTime(DateTimeFormatter formatter,
                                                          Charset charset,
                                                          String... encodeTimes) {

        val localDateTimes = new ArrayList<LocalDateTime>();
        for (val encodeTime : encodeTimes) {
            if (encodeTime == null || encodeTime.isBlank()) {
                localDateTimes.add(null);
                continue;
            }
            localDateTimes.add(LocalDateTime.from(formatter.parse(URLDecoder.decode(encodeTime, charset))));
        }
        return localDateTimes;
    }

    public static List<LocalDateTime> decodeLocalDateTime(DateTimeFormatter formatter,
                                                          String... encodeTimes) {
        return decodeLocalDateTime(formatter, StandardCharsets.UTF_8, encodeTimes);
    }
}
