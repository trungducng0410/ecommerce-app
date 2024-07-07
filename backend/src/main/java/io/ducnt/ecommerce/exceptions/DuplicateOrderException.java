package io.ducnt.ecommerce.exceptions;

public class DuplicateOrderException extends Exception {
    public DuplicateOrderException(String message) {
        super(message);
    }
}
