package io.ducnt.ecommerce.repositories;

import io.ducnt.ecommerce.models.Cart;
import io.ducnt.ecommerce.models.Product;
import io.ducnt.ecommerce.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByUserAndProduct(User user, Product product);
    List<Cart> findAllByUserOrderByCreatedAtDesc(User user);
}
