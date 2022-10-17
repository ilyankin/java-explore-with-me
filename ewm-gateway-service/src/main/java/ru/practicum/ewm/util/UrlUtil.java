package ru.practicum.ewm.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.val;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UrlUtil {
    public static String toArrayQueryParam(List<?> paramValues, String paramName) {
        if (paramName == null || paramName.isBlank() || paramValues == null || paramValues.size() == 0) return "";
        val result = new StringBuilder();
        for (val value : paramValues) {
            result
                    .append(paramName)
                    .append("=")
                    .append(value)
                    .append("&");
        }
        return result.deleteCharAt(result.length() - 1).toString();
    }
}
