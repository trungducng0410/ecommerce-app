package io.ducnt.ecommerce.repositories;

import io.ducnt.ecommerce.entities.Cart;
import io.ducnt.ecommerce.entities.Product;
import io.ducnt.ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByUserAndProduct(User user, Product product);
    List<Cart> findAllByUserOrderByCreatedAtDesc(User user);
    void deleteByUser(User user);
}
