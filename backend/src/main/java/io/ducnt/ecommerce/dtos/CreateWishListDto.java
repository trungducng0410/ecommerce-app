package io.ducnt.ecommerce.dtos;

import jakarta.validation.constraints.NotNull;

public record CreateWishListDto(
        @NotNull(message = "Product ID cannot be null")
        Integer productId
) {
}
