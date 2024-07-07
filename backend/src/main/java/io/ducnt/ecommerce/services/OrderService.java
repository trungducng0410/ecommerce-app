package io.ducnt.ecommerce.services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import io.ducnt.ecommerce.dtos.CheckoutItemDto;
import io.ducnt.ecommerce.dtos.CreateOrderDto;
import io.ducnt.ecommerce.dtos.StripeResponseDto;
import io.ducnt.ecommerce.entities.Cart;
import io.ducnt.ecommerce.entities.Order;
import io.ducnt.ecommerce.entities.OrderItem;
import io.ducnt.ecommerce.entities.User;
import io.ducnt.ecommerce.exceptions.AuthenticationFailException;
import io.ducnt.ecommerce.exceptions.DuplicateOrderException;
import io.ducnt.ecommerce.exceptions.InvalidOrderException;
import io.ducnt.ecommerce.exceptions.OrderNotFoundException;
import io.ducnt.ecommerce.repositories.CartRepository;
import io.ducnt.ecommerce.repositories.OrderItemRepository;
import io.ducnt.ecommerce.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final AuthenticationService authenticationService;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Value("${BASE_URL}")
    private String baseUrl;

    @Value("${STRIPE_SECRET_KEY}")
    private String apiKey;

    @Autowired
    public OrderService(AuthenticationService authenticationService, CartRepository cartRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.authenticationService = authenticationService;
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public StripeResponseDto createStripeSession(List<CheckoutItemDto> checkoutItemDtoList) throws StripeException {
        // supply success and failure url for stripe
        String successUrl = baseUrl + "payment/success";
        String failedUrl = baseUrl + "payment/failed";

        // set private key
        Stripe.apiKey = apiKey;

        List<SessionCreateParams.LineItem> sessionItemsList = new ArrayList<>();

        // generate session item
        for (CheckoutItemDto checkoutItemDto : checkoutItemDtoList) {
            sessionItemsList.add(createSessionLineItem(checkoutItemDto));
        }

        // build session param
        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCancelUrl(failedUrl)
                .addAllLineItem(sessionItemsList)
                .setSuccessUrl(successUrl)
                .build();

        Session session = Session.create(params);

        return new StripeResponseDto(session.getId(), session.getUrl());
    }

    private SessionCreateParams.LineItem createSessionLineItem(CheckoutItemDto checkoutItemDto) {
        return SessionCreateParams.LineItem.builder()
                .setPriceData(createPriceDate(checkoutItemDto))
                .setQuantity(Long.parseLong(String.valueOf(checkoutItemDto.quantity())))
                .build();
    }

    private SessionCreateParams.LineItem.PriceData createPriceDate(CheckoutItemDto checkoutItemDto) {
        return SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("aud")
                .setUnitAmount(((long) checkoutItemDto.price()) * 100)
                .setProductData(
                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                .setName(checkoutItemDto.productName())
                                .build()
                )
                .build();
    }

    @Transactional
    public void placeOrder(CreateOrderDto createOrderDto, String token) throws AuthenticationFailException, DuplicateOrderException, InvalidOrderException {
        // validate token
        authenticationService.authenticate(token);
        // retrieve user
        User user = authenticationService.getUser(token);

        // Check duplicated order
        Optional<Order> optionalOrder = orderRepository.findBySessionId(createOrderDto.sessionId());
        if (optionalOrder.isPresent()) {
            throw new DuplicateOrderException("Order is already existed");
        }

        // get cart items
        List<Cart> carts = cartRepository.findAllByUserOrderByCreatedAtDesc(user);
        if (carts.isEmpty()) {
            throw new InvalidOrderException("Order must contain at least 1 item");
        }

        // Calculate total price
        Double totalCost = carts.stream()
                .mapToDouble(cart -> cart.getQuantity() * cart.getProduct().getPrice())
                .sum();

        Date orderTime = new Date();

        Order newOrder = new Order();
        newOrder.setCreatedAt(orderTime);
        newOrder.setSessionId(createOrderDto.sessionId());
        newOrder.setUser(user);
        newOrder.setTotalPrice(totalCost);

        orderRepository.save(newOrder);

        for (Cart cart : carts) {
            OrderItem orderItem = new OrderItem();
            orderItem.setCreatedAt(orderTime);
            orderItem.setPrice(cart.getProduct().getPrice());
            orderItem.setProduct(cart.getProduct());
            orderItem.setQuantity(cart.getQuantity());
            orderItem.setOrder(newOrder);

            orderItemRepository.save(orderItem);
        }

        cartRepository.deleteByUser(user);
    }

    public List<Order> listOrdersByUser(String token) throws AuthenticationFailException {
        // validate token
        authenticationService.authenticate(token);
        // retrieve user
        User user = authenticationService.getUser(token);

        return orderRepository.findAllByUserOrderByCreatedAtDesc(user);
    }

    public Order getOrder(Integer id, String token) throws AuthenticationFailException, OrderNotFoundException {
        // validate token
        authenticationService.authenticate(token);
        // retrieve user
        User user = authenticationService.getUser(token);

        // get order
        Optional<Order> tmpOrder = orderRepository.findById(id);

        if (tmpOrder.isEmpty()) {
            throw new OrderNotFoundException("Order is not found with id " + id);
        }

        Order order = tmpOrder.get();

        // Check if order belong to current user
        if (!order.getUser().equals(user)) {
            throw new OrderNotFoundException("Order is not found with id " + id);
        }

        return order;
    }
}
