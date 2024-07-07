package io.ducnt.ecommerce.exceptions;

public class InvalidOrderException extends Exception {
    public InvalidOrderException(String message) {
        super(message);
    }
}
