package io.ducnt.ecommerce.services.impl;

import io.ducnt.ecommerce.dtos.CategoryDto;
import io.ducnt.ecommerce.dtos.CreateCategoryDto;
import io.ducnt.ecommerce.exceptions.CategoryNotFoundException;
import io.ducnt.ecommerce.exceptions.DuplicateCategoryException;
import io.ducnt.ecommerce.models.Category;
import io.ducnt.ecommerce.repositories.CategoryRepository;
import io.ducnt.ecommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
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

    @Override
    public List<CategoryDto> listCategories() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream().map(CategoryDto::new).toList();
    }

    @Override
    public CategoryDto updateCategory(int id, CreateCategoryDto updateCategoryDto) {
        Optional<Category> categoryTmp = categoryRepository.findById(id);
        if (categoryTmp.isPresent()) {
            Category category = categoryTmp.get();

            category.setCategoryName(updateCategoryDto.categoryName());
            category.setDescription(updateCategoryDto.description());
            category.setImageUrl(updateCategoryDto.imageUrl());

            categoryRepository.save(category);

            return new CategoryDto(category);
        } else {
            throw new CategoryNotFoundException("No category is found with id: " + id);
        }
    }
}
