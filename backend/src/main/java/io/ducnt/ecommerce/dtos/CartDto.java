package io.ducnt.ecommerce.dtos;

import java.util.List;

public record CartDto(
        List<CartItemDto> cartItems,
        Double totalCost
) {
}
