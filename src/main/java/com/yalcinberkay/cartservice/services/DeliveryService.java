package com.yalcinberkay.cartservice.services;

import com.yalcinberkay.cartservice.models.DTOs.CartDTO;
import com.yalcinberkay.cartservice.models.DTOs.CartItemDTO;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {
    private final CartService cartService;
    private static final Double FIXED_DELIVERY_COST = 2.99;
    private static final Double COST_PER_DELIVERY = 1.99;
    private static final Double COST_PER_PRODUCT = 1.99;

    DeliveryService(@Lazy final CartService cartService) {
        this.cartService = cartService;
    }

    public Double calculateByCart(final Long cartId) {
        final var cart = cartService.getById(cartId);
        final var numberOfProducts = getNumberOfProduct(cart);
        final var numberOfDeliveries = getNumberOfDeliveries(cart);
        return FIXED_DELIVERY_COST +
                (numberOfDeliveries * COST_PER_DELIVERY) +
                (numberOfProducts * COST_PER_PRODUCT);
    }

    private Long getNumberOfDeliveries(final CartDTO cart) {
        return cart.getItems()
                .stream()
                .map(i -> i.getProduct().getCategoryId())
                .distinct()
                .count();
    }

    private Long getNumberOfProduct(final CartDTO cart) {
        return cart.getItems()
                .stream()
                .map(CartItemDTO::getProductId)
                .distinct()
                .count();
    }
}
