package io.ducnt.ecommerce.services;

import io.ducnt.ecommerce.dtos.CategoryDto;
import io.ducnt.ecommerce.dtos.CreateCategoryDto;
import io.ducnt.ecommerce.models.Category;
import io.ducnt.ecommerce.repositories.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService service;

    @Test
    void givenCategory_whenReadCategory_thenReturnCategory() {
        Category book = new Category();
        book.setCategoryName("Book");
        book.setDescription("Book");

        when(categoryRepository.findByCategoryName("Book")).thenReturn(book);

        Category category = service.readCategory("Book");

        assertEquals(category, book);
    }

    @Test
    void givenNoCategory_whenReadCategory_thenReturnNull() {
        when(categoryRepository.findByCategoryName("Book")).thenReturn(null);

        Category category = service.readCategory("Book");

        assertNull(category);
    }

    @Test
    void givenValidCreateCategoryInput_whenCreateNewCategory_thenCallSaveRepoLayerOnce() {
        CreateCategoryDto createBookDto = CreateCategoryDto.builder().categoryName("Book").description("Book").build();

        Category book = new Category();
        book.setCategoryName("Book");
        book.setDescription("Book");

        when(categoryRepository.save(ArgumentMatchers.any())).thenReturn(book);

        service.createCategory(createBookDto);

        verify(categoryRepository, times(1)).save(book);
    }

    @Test
    void givenSomeCategories_whenListCategories_thenReturnAllCategories() {
        Category book = new Category(1, "Book", "Book", "book url");
        Category watch = new Category(2, "Watches", "Watches", "watch url");

        when(categoryRepository.findAll()).thenReturn(List.of(book, watch));

        List<CategoryDto> categories = service.listCategories();

        assertEquals(categories.size(), 2);
    }

    @Test
    void givenCategory_whenUpdateCategory_thenReturnUpdatedCategory() {
        Category category = new Category(1, "Book", "Book", "book url");
        Category updatedCategory = new Category(1, "Watch", "Watch", "watch url");
        CreateCategoryDto updateCategoryDto = new CreateCategoryDto("Watch", "Watch", "watch url");

        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        when(categoryRepository.save(any())).thenReturn(updatedCategory);

        CategoryDto updatedCat = service.updateCategory(1, updateCategoryDto);

        assertEquals(updatedCat.categoryName(), updateCategoryDto.categoryName());
        assertEquals(updatedCat.description(), updateCategoryDto.description());
        assertEquals(updatedCat.imageUrl(), updateCategoryDto.imageUrl());
    }
}