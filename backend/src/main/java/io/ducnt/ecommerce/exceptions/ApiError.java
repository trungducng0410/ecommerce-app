package io.ducnt.ecommerce.exceptions;

import java.time.LocalDateTime;

public record ApiError(
        String message,
        int statusCode,
        LocalDateTime localDateTime
) {
}
