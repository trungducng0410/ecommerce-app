package io.ducnt.ecommerce.exceptions;

public class DuplicateCartException extends Exception {
    public DuplicateCartException(String message) {
        super(message);
    }
}
