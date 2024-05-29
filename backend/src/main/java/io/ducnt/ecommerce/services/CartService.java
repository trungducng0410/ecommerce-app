package io.ducnt.ecommerce.services;

import io.ducnt.ecommerce.dtos.AddToCartDto;
import io.ducnt.ecommerce.dtos.CartDto;
import io.ducnt.ecommerce.dtos.CartItemDto;
import io.ducnt.ecommerce.exceptions.*;
import io.ducnt.ecommerce.models.Cart;
import io.ducnt.ecommerce.models.Product;
import io.ducnt.ecommerce.models.User;
import io.ducnt.ecommerce.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductService productService;
    private final AuthenticationService authenticationService;

    @Autowired
    public CartService(CartRepository cartRepository, ProductService productService, AuthenticationService authenticationService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.authenticationService = authenticationService;
    }

    public void addToCart(AddToCartDto addToCartDto, String token) throws AuthenticationFailException, DuplicateCartException, ProductNotFoundException {
        // Check user token
        authenticationService.authenticate(token);

        // Fetch user using token
        User user = authenticationService.getUser(token);

        // Get product
        Product product = productService.getProductById(addToCartDto.productId());

        // Check existing cart
        Optional<Cart> optionalCart = cartRepository.findByUserAndProduct(user, product);
        if (optionalCart.isPresent()) {
            throw new DuplicateCartException("This product is currently exiting in user cart");
        }

        // Save new cart
        Cart cart = new Cart(user, product, addToCartDto.quantity());
        cartRepository.save(cart);
    }

    public CartDto getCartItems(String token) throws AuthenticationFailException {
        // Check user token
        authenticationService.authenticate(token);

        // Fetch user
        User user = authenticationService.getUser(token);

        // Get all cart items by user
        List<Cart> carts = cartRepository.findAllByUserOrderByCreatedAtDesc(user);

        // Convert cart to dto
        List<CartItemDto> cartItemDtos = carts.stream()
                                            .map(cart -> new CartItemDto(cart.getId(),
                                                                    cart.getQuantity(),
                                                                    cart.getProduct()))
                                            .toList();

        // Calculate total price
        Double totalCost = carts.stream()
                .mapToDouble(cart -> cart.getQuantity() * cart.getProduct().getPrice())
                .sum();

        return new CartDto(cartItemDtos, totalCost);
    }

    public void deleteCartItem(int cartItemId, String token) throws AuthenticationFailException, CartItemNotFoundException, UnauthorizedException {
        // Check user token
        authenticationService.authenticate(token);

        // Fetch user
        User user = authenticationService.getUser(token);

        // Get cart by id
        Optional<Cart> cartOptional = cartRepository.findById(cartItemId);
        if (cartOptional.isEmpty()) {
            throw new CartItemNotFoundException("Cart item is not found with id " + cartItemId);
        }

        Cart cart = cartOptional.get();
        if (!cart.getUser().equals(user)) {
            throw new UnauthorizedException("User cannot remove cart item from other user");
        }

        cartRepository.delete(cart);
    }
}
