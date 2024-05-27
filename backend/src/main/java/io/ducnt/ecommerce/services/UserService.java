package io.ducnt.ecommerce.services;

import io.ducnt.ecommerce.configs.MessageStrings;
import io.ducnt.ecommerce.dtos.SignInDto;
import io.ducnt.ecommerce.dtos.SignInResponseDto;
import io.ducnt.ecommerce.dtos.SignUpResponseDto;
import io.ducnt.ecommerce.dtos.SignupDto;
import io.ducnt.ecommerce.exceptions.AuthenticationFailException;
import io.ducnt.ecommerce.exceptions.DuplicateUserException;
import io.ducnt.ecommerce.models.AuthenticationToken;
import io.ducnt.ecommerce.models.User;
import io.ducnt.ecommerce.repositories.UserRepository;
import jakarta.xml.bind.DatatypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;


    @Autowired
    public UserService(UserRepository userRepository, AuthenticationService authenticationService) {
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
    }


    public SignUpResponseDto signUp(SignupDto signupDto) throws DuplicateUserException {
        if (userRepository.findByEmail(signupDto.email()).isPresent()) {
            throw new DuplicateUserException("User already exists");
        }

        String encryptedPassword = signupDto.password();
        try {
            encryptedPassword = hashPassword(signupDto.password());
        } catch (NoSuchAlgorithmException ex) {
            logger.error("Hashing password failed {}", ex.getMessage());
        }

        User user = new User(signupDto.firstName(), signupDto.lastName(), signupDto.email(), encryptedPassword);

        userRepository.save(user);

        // TODO: create token when user login and set expire time for it to enhance security
        AuthenticationToken token = new AuthenticationToken(user);

        authenticationService.saveConfirmationToken(token);

        return new SignUpResponseDto("success", "user created successfully");
    }

    private static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest).toUpperCase();
    }

    public SignInResponseDto signIn(SignInDto signInDto) throws AuthenticationFailException {
        Optional<User> userOptional = userRepository.findByEmail(signInDto.email());
        if (userOptional.isEmpty()) {
            throw new AuthenticationFailException("user not present");
        }

        final User user = userOptional.get();

        try {
            String userPassword = user.getPassword();
            String signInHashedPassword = hashPassword(signInDto.password());

            if (!userPassword.equals(signInHashedPassword)) {
                throw new AuthenticationFailException(MessageStrings.WRONG_PASSWORD);
            }

        } catch (NoSuchAlgorithmException e) {
            logger.error("Hashing password failed {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        Optional<AuthenticationToken> token = authenticationService.getToken(user);

        if (token.isEmpty()) {
            throw new AuthenticationFailException(MessageStrings.AUTH_TOKEN_NOT_PRESENT);
        }

        return new SignInResponseDto("success", token.get().getToken());
    }
}
