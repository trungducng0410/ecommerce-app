package io.ducnt.ecommerce.services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import io.ducnt.ecommerce.dtos.CheckoutItemDto;
import io.ducnt.ecommerce.dtos.StripeResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final CartService cartService;

    @Value("${BASE_URL}")
    private String baseUrl;

    @Value("${STRIPE_SECRET_KEY}")
    private String apiKey;

    @Autowired
    public OrderService(CartService cartService) {
        this.cartService = cartService;
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
}
