package io.ducnt.ecommerce.dtos;

import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.Length;

public record SignInDto(
        @Email(message = "Email has invalid format")
        String email,
        @Length(min = 8, message = "Password must contain at least 8 characters")
        String password
) {
}
