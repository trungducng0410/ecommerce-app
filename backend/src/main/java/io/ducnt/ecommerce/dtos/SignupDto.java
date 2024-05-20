package io.ducnt.ecommerce.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record SignupDto(
        @NotBlank(message = "First name cannot be empty")
        String firstName,
        String lastName,
        @Email(message = "Email has invalid format")
        String email,
        @Length(min = 8, message = "Password must contain at least 8 characters")
        String password
) {
}
