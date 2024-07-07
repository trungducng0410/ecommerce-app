package io.ducnt.ecommerce.repositories;

import io.ducnt.ecommerce.entities.Order;
import io.ducnt.ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByUserOrderByCreatedAtDesc(User user);
    Optional<Order> findBySessionId(String sessionId);
}
