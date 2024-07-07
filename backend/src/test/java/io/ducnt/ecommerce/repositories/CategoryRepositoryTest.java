package io.ducnt.ecommerce.repositories;

import io.ducnt.ecommerce.entities.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository repository;

    @Test
    void givenCategoryAndCorrectName_whenFindCategoryByName_thenReturnCategory() {
        Category book = new Category();
        book.setCategoryName("Book");
        book.setDescription("Book");

        repository.save(book);

        Category category = repository.findByCategoryName("Book");

        assertEquals(category, book);
    }
}