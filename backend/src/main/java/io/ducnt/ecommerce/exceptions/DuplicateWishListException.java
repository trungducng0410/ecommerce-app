package io.ducnt.ecommerce.exceptions;

public class DuplicateWishListException extends Exception {
    public DuplicateWishListException(String message) {
        super(message);
    }
}