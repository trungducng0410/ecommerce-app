package io.ducnt.ecommerce.repositories;

import io.ducnt.ecommerce.models.Category;
import io.ducnt.ecommerce.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class ProductRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findAllByCategoryId() {
        Category category = new Category();
        category.setId(1);
        category.setCategoryName("Category");

        Product product = new Product();
        product.setId(1);
        product.setName("Product");
        product.setPrice(1.0);
        product.setCategory(category);

        categoryRepository.save(category);
        productRepository.save(product);

        List<Product> result = productRepository.findAllByCategoryId(1);

        assertEquals(result.size(), 1);
        assertEquals(result.get(0), product);
    }
}