package ru.practicum.ewm.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.val;

import java.util.Collection;
import java.util.function.Predicate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UrlUtil {
    public static <T> String toArrayQueryParam(Collection<T> paramValues, String paramName, Predicate<T> filter) {
        if (paramName == null || paramName.isBlank() || paramValues == null || paramValues.size() == 0) return "";
        val result = new StringBuilder();
        for (val value : paramValues) {
            if (filter.test(value)) {
                result
                        .append(paramName)
                        .append("=")
                        .append(value)
                        .append("&");
            }
        }

        if (result.length() == 0) return "";

        return result.toString();
    }
}
