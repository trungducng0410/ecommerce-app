package io.ducnt.ecommerce.dtos;

public record StripeResponseDto(
        String sessionId,
        String url) {
}
