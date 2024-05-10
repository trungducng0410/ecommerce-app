package io.ducnt.ecommerce.service.impl;

import io.ducnt.ecommerce.dto.CreateCategoryDto;
import io.ducnt.ecommerce.model.Category;
import io.ducnt.ecommerce.repository.CategoryRepository;
import io.ducnt.ecommerce.service.CategoryService;
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

    public void createCategory(CreateCategoryDto createCategoryDto) {
        Category newCategory = new Category();
        newCategory.setCategoryName(createCategoryDto.categoryName());
        newCategory.setDescription(createCategoryDto.description());
        newCategory.setImageUrl(createCategoryDto.imageUrl());

        categoryRepository.save(newCategory);
    }
}
