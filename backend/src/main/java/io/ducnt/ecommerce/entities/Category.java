package io.ducnt.ecommerce.entities;

import io.ducnt.ecommerce.dtos.CreateCategoryDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "category_name")
    private String categoryName;

    private String description;

    private String imageUrl;

    public Category(CreateCategoryDto createCategoryDto) {
        this.categoryName = createCategoryDto.categoryName();
        this.description = createCategoryDto.description();
        this.imageUrl = createCategoryDto.imageUrl();
    }
}
