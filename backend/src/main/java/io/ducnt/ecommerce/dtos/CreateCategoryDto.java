package io.ducnt.ecommerce.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;


@Builder
public record CreateCategoryDto(
        @NotBlank(message = "Invalid category name. Name should not be blank") String categoryName,
        String description,
        String imageUrl) {
}
