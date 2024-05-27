package io.ducnt.ecommerce.services;

import io.ducnt.ecommerce.dtos.CreateWishListDto;
import io.ducnt.ecommerce.dtos.ProductDto;
import io.ducnt.ecommerce.exceptions.*;
import io.ducnt.ecommerce.models.Product;
import io.ducnt.ecommerce.models.User;
import io.ducnt.ecommerce.models.WishList;
import io.ducnt.ecommerce.repositories.ProductRepository;
import io.ducnt.ecommerce.repositories.WishListRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishListService {
    private final Logger logger = LoggerFactory.getLogger(WishListService.class);
    private final WishListRepository wishListRepository;
    private final ProductRepository productRepository;
    private final AuthenticationService authenticationService;

    @Autowired
    public WishListService(WishListRepository wishListRepository, ProductRepository productRepository, AuthenticationService authenticationService) {
        this.wishListRepository = wishListRepository;
        this.productRepository = productRepository;
        this.authenticationService = authenticationService;
    }

    public void createWishList(CreateWishListDto createWishListDto, String authToken) throws AuthenticationFailException {
        // Check user token
        authenticationService.authenticate(authToken);

        // Fetch user using token
        User user = authenticationService.getUser(authToken);

        // Get product
        Integer productId = createWishListDto.productId();
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isEmpty()) {
            this.logger.error("Product is not found with id {}", productId);
            throw new ProductNotFoundException("Product is not found with id: " + productId);
        }

        // Check duplicate wishlist
        Product product = optionalProduct.get();
        Optional<WishList> existingWishList = wishListRepository.findByUserAndProduct(user, product);
        if (existingWishList.isPresent()) {
            this.logger.error("Wishlist is already existed for user {} with product {}", user, product);
            throw new DuplicateWishListException("This product is already exist in current user wishlist");
        }

        // Save wishlist
        WishList wishList = new WishList(user, optionalProduct.get());
        wishListRepository.save(wishList);
    }

    public List<ProductDto> getWishList(String authToken) throws AuthenticationFailException {
        // Check user token
        authenticationService.authenticate(authToken);

        // Fetch user using token
        User user = authenticationService.getUser(authToken);

        List<WishList> wishLists = wishListRepository.findAllByUserOrderByCreatedAtDesc(user);

        return wishLists.stream()
                .map(wishList -> new ProductDto(wishList.getProduct()))
                .toList();
    }

    public void deleteWishList(Integer wishListId, String authToken) throws AuthenticationFailException {
        // Check user token
        authenticationService.authenticate(authToken);

        // Fetch user using token
        User user = authenticationService.getUser(authToken);

        // Get wishlist
        Optional<WishList> optionalWishList = wishListRepository.findById(wishListId);
        if (optionalWishList.isEmpty()) {
            this.logger.error("Wishlist is not found with id {}", wishListId);
            throw new WishListNotFoundException("Wishlist is not found with id " + wishListId);
        }

        WishList wishList = optionalWishList.get();
        if (!wishList.getUser().equals(user)) {
            this.logger.error("User cannot remove wishlist from other user");
            throw new UnauthorizedException("User cannot remove wishlist from other user");
        }

        wishListRepository.delete(wishList);
    }
}
