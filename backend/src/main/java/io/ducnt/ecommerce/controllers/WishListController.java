package io.ducnt.ecommerce.controllers;

import io.ducnt.ecommerce.dtos.ApiResponseDto;
import io.ducnt.ecommerce.dtos.CreateWishListDto;
import io.ducnt.ecommerce.dtos.ProductDto;
import io.ducnt.ecommerce.exceptions.AuthenticationFailException;
import io.ducnt.ecommerce.services.WishListService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlists")
public class WishListController {
    private final WishListService service;

    @Autowired
    public WishListController(WishListService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<ApiResponseDto> createWishList(@RequestBody @Valid CreateWishListDto createWishListDto,
                                                         @RequestHeader("Authorization") @NotNull String token) {
        try {
            service.createWishList(createWishListDto, token);
            return new ResponseEntity<>(new ApiResponseDto(true, "Added to wishlist"), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponseDto(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public List<ProductDto> getWishList(@RequestHeader("Authorization") @NotNull String token) throws AuthenticationFailException {
        return service.getWishList(token);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto> deleteWishList(@PathVariable("id") Integer wishListId,
                                                             @RequestHeader("Authorization") @NotNull String token) {
        try {
            service.deleteWishList(wishListId, token);
            return new ResponseEntity<>(new ApiResponseDto(true, "Removed product from wishlist"), HttpStatus.CREATED);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(new ApiResponseDto(false, ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
