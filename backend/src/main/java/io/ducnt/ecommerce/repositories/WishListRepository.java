package io.ducnt.ecommerce.repositories;

import io.ducnt.ecommerce.models.Product;
import io.ducnt.ecommerce.models.User;
import io.ducnt.ecommerce.models.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Integer> {
    List<WishList> findAllByUserOrderByCreatedAtDesc(User user);
    Optional<WishList> findByUserAndProduct(User user, Product product);
}
