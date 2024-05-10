package io.ducnt.ecommerce.controllers;

import io.ducnt.ecommerce.dtos.CategoryDto;
import io.ducnt.ecommerce.dtos.CreateCategoryDto;
import io.ducnt.ecommerce.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody @Valid CreateCategoryDto createCategoryDto) {
        CategoryDto categoryDto = categoryService.createCategory(createCategoryDto);
        return new ResponseEntity<>(categoryDto, HttpStatus.CREATED);
    }

    @GetMapping
    public List<CategoryDto> getCategories() {
        return categoryService.listCategories();
    }

    @PutMapping("/{id}")
    public CategoryDto updateCategory(@PathVariable int id, @RequestBody @Valid CreateCategoryDto updateCategoryDto) {
        return categoryService.updateCategory(id, updateCategoryDto);
    }
}
