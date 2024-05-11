package io.ducnt.ecommerce.services.impl;

import io.ducnt.ecommerce.dtos.CategoryDto;
import io.ducnt.ecommerce.dtos.CreateCategoryDto;
import io.ducnt.ecommerce.exceptions.DuplicateCategoryException;
import io.ducnt.ecommerce.models.Category;
import io.ducnt.ecommerce.repositories.CategoryRepository;
import io.ducnt.ecommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category readCategory(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }

    public CategoryDto createCategory(CreateCategoryDto createCategoryDto) {
        Category existingCategory = categoryRepository.findByCategoryName(createCategoryDto.categoryName());
        if (existingCategory != null) {
            throw new DuplicateCategoryException("Category is existed");
        }

        Category newCategory = new Category();
        newCategory.setCategoryName(createCategoryDto.categoryName());
        newCategory.setDescription(createCategoryDto.description());
        newCategory.setImageUrl(createCategoryDto.imageUrl());

        Category createdCategory = categoryRepository.save(newCategory);

        return new CategoryDto(createdCategory);
    }
}
