package io.ducnt.ecommerce.controllers;

import io.ducnt.ecommerce.dtos.SignInDto;
import io.ducnt.ecommerce.dtos.SignInResponseDto;
import io.ducnt.ecommerce.dtos.SignUpResponseDto;
import io.ducnt.ecommerce.dtos.SignupDto;
import io.ducnt.ecommerce.exceptions.AuthenticationFailException;
import io.ducnt.ecommerce.exceptions.DuplicateUserException;
import io.ducnt.ecommerce.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public SignUpResponseDto signUp(@RequestBody @Valid SignupDto signupDto) throws DuplicateUserException {
        return userService.signUp(signupDto);
    }

    @PostMapping("/signin")
    public SignInResponseDto signIn(@RequestBody @Valid SignInDto signInDto) throws AuthenticationFailException {
        return userService.signIn(signInDto);
    }
}
