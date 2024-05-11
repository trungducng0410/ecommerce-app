package io.ducnt.ecommerce.services;

import io.ducnt.ecommerce.dtos.CategoryDto;
import io.ducnt.ecommerce.dtos.CreateCategoryDto;
import io.ducnt.ecommerce.models.Category;

public interface CategoryService {
    Category readCategory(String categoryName);
    CategoryDto createCategory(CreateCategoryDto createCategoryDto);
}
