package com.yalcinberkay.cartservice.converters;

import com.yalcinberkay.cartservice.entities.CartItem;
import com.yalcinberkay.cartservice.models.DTOs.CartItemDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CartItemEntityToDTOConverter implements Function<CartItem, CartItemDTO> {

    @Override
    public CartItemDTO apply(final CartItem cartItem) {
        return CartItemDTO.builder()
                .cartId(cartItem.getCartId())
                .amount(cartItem.getAmount())
                .id(cartItem.getCartId())
                .discount(cartItem.getDiscount())
                .productId(cartItem.getProductId())
                .quantity(cartItem.getQuantity())
                .unitPrice(cartItem.getUnitPrice())
                .build();
    }
}
