package io.ducnt.ecommerce.exceptions;

public class DuplicateWishListException extends RuntimeException {
    public DuplicateWishListException(String message) {
        super(message);
    }
}