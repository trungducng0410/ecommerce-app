package io.ducnt.ecommerce.services;

import io.ducnt.ecommerce.dtos.CreateProductDto;
import io.ducnt.ecommerce.dtos.ProductDto;
import io.ducnt.ecommerce.exceptions.CategoryNotFoundException;
import io.ducnt.ecommerce.exceptions.ProductNotFoundException;
import io.ducnt.ecommerce.models.Category;
import io.ducnt.ecommerce.models.Product;
import io.ducnt.ecommerce.repositories.CategoryRepository;
import io.ducnt.ecommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    public ProductDto createProduct(CreateProductDto createProductDto) {
        Integer categoryId = createProductDto.categoryId();
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isEmpty()) {
            throw new CategoryNotFoundException("No category is found with id: " + categoryId);
        }
        Category category = optionalCategory.get();
        Product newProduct = new Product(createProductDto, category);

        Product createdProduct = productRepository.save(newProduct);

        return new ProductDto(createdProduct);
    }

    public List<ProductDto> listProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductDto::new)
                .toList();
    }

    public ProductDto getProductById(int id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("Product is not found with id: " + id);
        }

        return new ProductDto(optionalProduct.get());
    }

    public ProductDto updateProduct(int id, CreateProductDto updateProductDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("Product is not found with id: " + id);
        }

        Optional<Category> optionalCategory = categoryRepository.findById(updateProductDto.categoryId());
        if (optionalCategory.isEmpty()) {
            throw new CategoryNotFoundException("No category is found with id: " + updateProductDto.categoryId());
        }

        Product product = optionalProduct.get();
        product.setName(updateProductDto.name());
        product.setDescription(updateProductDto.description());
        product.setPrice(updateProductDto.price());
        product.setImageUrl(updateProductDto.imageUrl());
        product.setCategory(optionalCategory.get());

        Product updatedProduct = productRepository.save(product);

        return new ProductDto(updatedProduct);
    }
}
