package io.ducnt.ecommerce.dtos;

import java.util.Date;

public record ApiResponseDto(
        Boolean success,
        String message,
        Date timestamp
) {
    public ApiResponseDto(Boolean success, String message) {
        this(success, message, new Date());
    }
}
