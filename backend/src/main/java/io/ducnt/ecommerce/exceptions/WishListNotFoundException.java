package io.ducnt.ecommerce.exceptions;

public class WishListNotFoundException extends RuntimeException {
    public WishListNotFoundException(String message) {
        super(message);
    }
}
