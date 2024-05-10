package io.ducnt.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;


@Builder
public record CreateCategoryDto(@NotBlank String categoryName, String description, String imageUrl) {
}
