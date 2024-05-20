package io.ducnt.ecommerce.repositories;

import io.ducnt.ecommerce.models.AuthenticationToken;
import io.ducnt.ecommerce.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticationTokenRepository extends JpaRepository<AuthenticationToken, Integer> {
    Optional<AuthenticationToken> findTokenByUser(User user);
    Optional<AuthenticationToken> findTokenByToken(String token);
}
