package io.ducnt.ecommerce.exceptions;

public class DuplicateUserException extends Exception {
    public DuplicateUserException(String message) {
        super(message);
    }
}
