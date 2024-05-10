package io.ducnt.ecommerce.controllers;

import io.ducnt.ecommerce.dtos.CreateProductDto;
import io.ducnt.ecommerce.dtos.ProductDto;
import io.ducnt.ecommerce.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody @Valid CreateProductDto createProductDto) {
        ProductDto createdProduct = productService.createProduct(createProductDto);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping
    public List<ProductDto> getProducts() {
        return productService.listProducts();
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public ProductDto updateProduct(@PathVariable int id, @RequestBody @Valid CreateProductDto updateProductDto) {
        return productService.updateProduct(id, updateProductDto);
    }
}
