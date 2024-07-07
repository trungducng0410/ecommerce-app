package io.ducnt.ecommerce.controllers;

import com.stripe.exception.StripeException;
import io.ducnt.ecommerce.dtos.ApiResponseDto;
import io.ducnt.ecommerce.dtos.CheckoutItemDto;
import io.ducnt.ecommerce.dtos.CreateOrderDto;
import io.ducnt.ecommerce.dtos.StripeResponseDto;
import io.ducnt.ecommerce.entities.Order;
import io.ducnt.ecommerce.exceptions.AuthenticationFailException;
import io.ducnt.ecommerce.exceptions.OrderNotFoundException;
import io.ducnt.ecommerce.services.OrderService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable("id") Integer id, @RequestHeader("Authorization") @NotNull String token) throws AuthenticationFailException, OrderNotFoundException {
        return orderService.getOrder(id, token);
    }

    @GetMapping()
    public List<Order> getAllOrders(@RequestHeader("Authorization") @NotNull String token) throws AuthenticationFailException {
        return orderService.listOrdersByUser(token);
    }

    @PostMapping("/create-checkout-session")
    public ResponseEntity<StripeResponseDto> checkoutList(@RequestBody List<CheckoutItemDto> checkoutItemDtoList) throws StripeException {
        StripeResponseDto stripeResponse = orderService.createStripeSession(checkoutItemDtoList);

        return new ResponseEntity<>(stripeResponse, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponseDto> placeOrder(@RequestBody CreateOrderDto createOrderDto,
                                                     @RequestHeader("Authorization") @NotNull String token) {
        try {
            orderService.placeOrder(createOrderDto, token);
            return new ResponseEntity<>(new ApiResponseDto(true, "Order has been placed"), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ApiResponseDto(false, ex.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }
}
