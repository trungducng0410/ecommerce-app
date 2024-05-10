package io.ducnt.ecommerce.controller;

import io.ducnt.ecommerce.common.ApiResponse;
import io.ducnt.ecommerce.dto.CreateCategoryDto;
import io.ducnt.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createCategory(@RequestBody @Valid CreateCategoryDto createCategoryDto) {
        if (Objects.nonNull(categoryService.readCategory(createCategoryDto.categoryName()))) {
            return new ResponseEntity<>(
                    new ApiResponse(false, "Category already exists"),
                    HttpStatus.CONFLICT
            );
        }

        categoryService.createCategory(createCategoryDto);

        return new ResponseEntity<>(
                new ApiResponse(true, "The category is created"),
                HttpStatus.CREATED
        );
    }
}
