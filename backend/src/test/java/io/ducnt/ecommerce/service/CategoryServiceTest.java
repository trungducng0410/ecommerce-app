package io.ducnt.ecommerce.service;

import io.ducnt.ecommerce.dto.CreateCategoryDto;
import io.ducnt.ecommerce.model.Category;
import io.ducnt.ecommerce.repository.CategoryRepository;
import io.ducnt.ecommerce.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl service;

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
        CreateCategoryDto createBookDto = new CreateCategoryDto();
        createBookDto.setCategoryName("Book");
        createBookDto.setDescription("Book");

        Category book = new Category();
        book.setCategoryName("Book");
        book.setDescription("Book");

        when(categoryRepository.save(ArgumentMatchers.any())).thenReturn(book);

        service.createCategory(createBookDto);

        verify(categoryRepository, times(1)).save(book);
    }
}