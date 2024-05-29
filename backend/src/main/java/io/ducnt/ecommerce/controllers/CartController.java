package io.ducnt.ecommerce.controllers;

import io.ducnt.ecommerce.dtos.AddToCartDto;
import io.ducnt.ecommerce.dtos.ApiResponseDto;
import io.ducnt.ecommerce.dtos.CartDto;
import io.ducnt.ecommerce.exceptions.AuthenticationFailException;
import io.ducnt.ecommerce.services.CartService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    private final CartService service;

    @Autowired
    public CartController(CartService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto> addToCart(@RequestBody @Valid AddToCartDto addToCartDto,
                                                    @RequestHeader("Authorization") @NotNull String token) {
        try {
            service.addToCart(addToCartDto, token);
            return new ResponseEntity<>(new ApiResponseDto(true, "Added to cart"), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ApiResponseDto(false, ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public CartDto getCartItems(@RequestHeader("Authorization") @NotNull String token) throws AuthenticationFailException {
        return service.getCartItems(token);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto> deleteCartItem(@PathVariable("id") int cartItemId,
                                                      @RequestHeader("Authorization") @NotNull String token) {
        try {
            service.deleteCartItem(cartItemId, token);
            return new ResponseEntity<>(new ApiResponseDto(true, "Item has been removed"), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ApiResponseDto(false, ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
