package io.ducnt.ecommerce.dtos;

public record CheckoutItemDto(
        String productName,
        int quantity,
        double price,
        long productId
) {
}
