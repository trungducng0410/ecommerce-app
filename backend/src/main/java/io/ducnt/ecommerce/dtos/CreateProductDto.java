package io.ducnt.ecommerce.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

public record CreateProductDto(
        @NotBlank(message = "Product name cannot be blank")
        String name,

        @NotBlank(message = "Image URL cannot be blank")
        @URL(message = "Image url has incorrect format")
        String imageUrl,

        @Min(value = 0,message = "Price must greater than zero")
        Double price,

        String description,

        @NotNull(message = "Category id must not be null")
        Integer categoryId
) {}
