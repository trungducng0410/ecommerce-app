package io.ducnt.ecommerce.dtos;


import io.ducnt.ecommerce.entities.Product;

public record CartItemDto(
        Integer id,
        Integer quantity,
        ProductDto product
) {
    public CartItemDto(Integer id, Integer quantity, Product product) {
        this(id, quantity, new ProductDto(product));
    }
}
