package io.ducnt.ecommerce.services;

import io.ducnt.ecommerce.dtos.CategoryDto;
import io.ducnt.ecommerce.dtos.CreateCategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CreateCategoryDto createCategoryDto);
    List<CategoryDto> listCategories();
    CategoryDto updateCategory(int id, CreateCategoryDto updateCategoryDto);
}
