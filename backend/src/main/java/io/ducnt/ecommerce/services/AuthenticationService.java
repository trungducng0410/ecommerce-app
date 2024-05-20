package io.ducnt.ecommerce.services;

import io.ducnt.ecommerce.configs.MessageStrings;
import io.ducnt.ecommerce.exceptions.AuthenticationFailException;
import io.ducnt.ecommerce.models.AuthenticationToken;
import io.ducnt.ecommerce.models.User;
import io.ducnt.ecommerce.repositories.AuthenticationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class AuthenticationService {

    private final AuthenticationTokenRepository repository;

    @Autowired
    public AuthenticationService(AuthenticationTokenRepository repository) {
        this.repository = repository;
    }

    public void saveConfirmationToken(AuthenticationToken token) {
        repository.save(token);
    }

    public Optional<AuthenticationToken> getToken(User user) {
        return repository.findTokenByUser(user);
    }

    public User getUser(String token) {
        Optional<AuthenticationToken> authToken = repository.findTokenByToken(token);
        return authToken.map(AuthenticationToken::getUser).orElse(null);
    }

    public void authenticate(String token) throws AuthenticationFailException {
        if (Objects.isNull(token)) {
            throw new AuthenticationFailException(MessageStrings.AUTH_TOKEN_NOT_PRESENT);
        }
        if (Objects.isNull(getUser(token))) {
            throw new AuthenticationFailException(MessageStrings.AUTH_TOKEN_NOT_VALID);
        }
    }
}
