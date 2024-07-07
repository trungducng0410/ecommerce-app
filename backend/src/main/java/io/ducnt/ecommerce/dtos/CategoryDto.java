package io.ducnt.ecommerce.dtos;

import io.ducnt.ecommerce.entities.Category;
import lombok.Builder;

@Builder
public record CategoryDto(
        Integer id,
        String categoryName,
        String description,
        String imageUrl


) {
    public CategoryDto(Category category) {
        this(
                category.getId(),
                category.getCategoryName(),
                category.getDescription(),
                category.getImageUrl()
        );
    }
}
