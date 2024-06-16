package io.ducnt.ecommerce.controllers;

import com.stripe.exception.StripeException;
import io.ducnt.ecommerce.dtos.CheckoutItemDto;
import io.ducnt.ecommerce.dtos.StripeResponseDto;
import io.ducnt.ecommerce.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create-checkout-session")
    public ResponseEntity<StripeResponseDto> checkoutList(@RequestBody List<CheckoutItemDto> checkoutItemDtoList) throws StripeException {
        StripeResponseDto stripeResponse = orderService.createStripeSession(checkoutItemDtoList);

        return new ResponseEntity<>(stripeResponse, HttpStatus.OK);
    }
}
