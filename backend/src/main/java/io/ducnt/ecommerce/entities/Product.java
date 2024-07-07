package io.ducnt.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ducnt.ecommerce.dtos.CreateProductDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String imageUrl;
    private Double price;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonIgnore
    private Category category;

    public Product(CreateProductDto createProductDto, Category category) {
        this.name = createProductDto.name();
        this.imageUrl = createProductDto.imageUrl();
        this.price = createProductDto.price();
        this.description = createProductDto.description();
        this.category = category;
    }
}
