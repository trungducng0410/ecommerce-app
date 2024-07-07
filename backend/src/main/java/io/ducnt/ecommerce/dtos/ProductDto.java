package io.ducnt.ecommerce.dtos;

import io.ducnt.ecommerce.entities.Product;

public record ProductDto(
        Integer id,
        String name,
        String imageUrl,
        Double price,
        String description
) {
    public ProductDto(Product product) {
        this(
                product.getId(),
                product.getName(),
                product.getImageUrl(),
                product.getPrice(),
                product.getDescription()
        );
    }
}
