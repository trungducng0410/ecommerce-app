package io.ducnt.ecommerce.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AddToCartDto(
        @NotNull
        Integer productId,
        @NotNull
        @Min(1)
        Integer quantity
) {
}
