package io.ducnt.ecommerce.repositories;

import io.ducnt.ecommerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT * FROM products p WHERE p.category_id = :categoryId", nativeQuery = true)
    List<Product> findAllByCategoryId(int categoryId);
}
