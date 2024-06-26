package io.ducnt.ecommerce.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.hibernate.validator.constraints.URL;


@Builder
public record CreateCategoryDto(
        @NotBlank(message = "Invalid category name. Name should not be blank")
        String categoryName,

        String description,

        @URL(message = "Image url is in incorrect format")
        String imageUrl
) {}
