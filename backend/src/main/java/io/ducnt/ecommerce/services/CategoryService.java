package io.ducnt.ecommerce.services;

import io.ducnt.ecommerce.dtos.CategoryDto;
import io.ducnt.ecommerce.dtos.CreateCategoryDto;
import io.ducnt.ecommerce.exceptions.CategoryNotFoundException;
import io.ducnt.ecommerce.exceptions.DuplicateCategoryException;
import io.ducnt.ecommerce.models.Category;
import io.ducnt.ecommerce.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category readCategory(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }

    public CategoryDto createCategory(CreateCategoryDto createCategoryDto) throws DuplicateCategoryException {
        Category existingCategory = categoryRepository.findByCategoryName(createCategoryDto.categoryName());
        if (existingCategory != null) {
            throw new DuplicateCategoryException("Category is existed");
        }
        Category newCategory = new Category(createCategoryDto);

        Category createdCategory = categoryRepository.save(newCategory);

        return new CategoryDto(createdCategory);
    }

    public List<CategoryDto> listCategories() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream().map(CategoryDto::new).toList();
    }

    public CategoryDto updateCategory(int id, CreateCategoryDto updateCategoryDto) throws CategoryNotFoundException {
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

    public CategoryDto getCategory(int id) throws CategoryNotFoundException {
        Optional<Category> categoryTmp = categoryRepository.findById(id);
        if (categoryTmp.isEmpty()) {
            throw new CategoryNotFoundException("No category is found with id: " + id);
        }

        return new CategoryDto(categoryTmp.get());
    }
}
