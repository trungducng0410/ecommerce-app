package io.ducnt.ecommerce.dtos;

import jakarta.validation.constraints.NotEmpty;

public record CreateOrderDto(
        @NotEmpty
        String sessionId
) {
}
