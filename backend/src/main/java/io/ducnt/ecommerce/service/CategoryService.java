package io.ducnt.ecommerce.service;

import io.ducnt.ecommerce.dto.CreateCategoryDto;
import io.ducnt.ecommerce.model.Category;

public interface CategoryService {
    Category readCategory(String categoryName);
    void createCategory(CreateCategoryDto createCategoryDto);
}
