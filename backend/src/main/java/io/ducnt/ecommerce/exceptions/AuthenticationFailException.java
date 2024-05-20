package io.ducnt.ecommerce.exceptions;

public class AuthenticationFailException extends Exception {
    public AuthenticationFailException(String message) {
        super(message);
    }
}
