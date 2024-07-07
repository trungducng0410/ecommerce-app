package io.ducnt.ecommerce.services;

import io.ducnt.ecommerce.dtos.CreateProductDto;
import io.ducnt.ecommerce.dtos.ProductDto;
import io.ducnt.ecommerce.exceptions.CategoryNotFoundException;
import io.ducnt.ecommerce.exceptions.ProductNotFoundException;
import io.ducnt.ecommerce.entities.Category;
import io.ducnt.ecommerce.entities.Product;
import io.ducnt.ecommerce.repositories.CategoryRepository;
import io.ducnt.ecommerce.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService service;

    @Test
    void givenValidCreateProductInput_whenCreateProduct_thenReturnNewProduct() throws CategoryNotFoundException {
        CreateProductDto createProductDto = new CreateProductDto("Macbook Air", "https://www.test.com/", 1000.0, "Apple Macbook Air 2024", 1);
        Category category = new Category(1, "Laptop", "Personal Computer", "https://www.test.com/");

        Product newProduct = new Product(createProductDto, category);
        newProduct.setId(1);

        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        when(productRepository.save(any())).thenReturn(newProduct);

        ProductDto createdProduct = service.createProduct(createProductDto);

        assertEquals(createdProduct.id(), newProduct.getId());
        assertEquals(createdProduct.name(), newProduct.getName());
        assertEquals(createdProduct.imageUrl(), newProduct.getImageUrl());
        assertEquals(createdProduct.price(), newProduct.getPrice());
        assertEquals(createdProduct.description(), newProduct.getDescription());
    }

    @Test
    void givenSomeProducts_whenListAllProducts_thenReturnAllProducts() {
        Product laptop = new Product();
        laptop.setId(1);
        laptop.setName("Macbook Air");
        laptop.setDescription("Apple Macbook Air 2024");
        laptop.setPrice(1500.0);
        laptop.setImageUrl("https://www.test.com/");

        List<Product> products = List.of(laptop);

        when(productRepository.findAll()).thenReturn(products);

        List<ProductDto> allProducts = service.listProducts();

        assertEquals(allProducts.size(), products.size());
        assertEquals(allProducts.get(0).id(), laptop.getId());
    }

    @Test
    void givenProduct_whenGetProductById_thenReturnProductDto() throws ProductNotFoundException {
        Product laptop = new Product();
        laptop.setId(1);
        laptop.setName("Macbook Air");
        laptop.setDescription("Apple Macbook Air 2024");
        laptop.setPrice(1500.0);
        laptop.setImageUrl("https://www.test.com/");

        when(productRepository.findById(1)).thenReturn(Optional.of(laptop));

        ProductDto productDto = service.getProduct(1);

        assertNotNull(productDto);
        assertEquals(productDto.id(), laptop.getId());
    }

    @Test
    void givenProduct_whenUpdateProduct_thenReturnUpdatedProduct() throws CategoryNotFoundException, ProductNotFoundException {
        Category category = new Category(1, "Laptop", "Personal Computer", "https://www.test.com/");
        Product laptop = new Product(1, "Macbook Air", "https://www.test.com/", 1500.0, "Apple Macbook Air 2024", category);
        CreateProductDto updateProductDto = new CreateProductDto("Macbook Pro", "https://www.test.com/", 1000.0, "Apple Macbook Air 2024", 1);

        when(productRepository.findById(1)).thenReturn(Optional.of(laptop));
        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        when(productRepository.save(laptop)).thenReturn(laptop);

        ProductDto updatedProduct = service.updateProduct(1, updateProductDto);

        assertNotNull(updatedProduct);
        assertEquals(updatedProduct.id(), laptop.getId());
        assertEquals(updatedProduct.name(), laptop.getName());
        assertEquals(updatedProduct.name(), updateProductDto.name());
    }
}