package com.yalcinberkay.cartservice.controllers;

import com.yalcinberkay.cartservice.models.DTOs.CartDTO;
import com.yalcinberkay.cartservice.models.requests.ApplyCouponDiscountRequest;
import com.yalcinberkay.cartservice.models.requests.CartItemAddRequest;
import com.yalcinberkay.cartservice.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/carts")
@RestController
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PutMapping("{cartId}")
    @ResponseStatus(HttpStatus.OK)
    public CartDTO addItem(@PathVariable final Long cartId, @RequestBody final CartItemAddRequest cartItemAddRequest) {
        return cartService.addItem(cartItemAddRequest);
    }

    @PutMapping("{cartId}/apply-coupon")
    @ResponseStatus(HttpStatus.OK)
    public CartDTO applyCoupon(@PathVariable final Long cartId, @RequestBody final ApplyCouponDiscountRequest applyCouponDiscountRequest) {
        return cartService.applyCoupon(applyCouponDiscountRequest);
    }

    @GetMapping("{cartId}")
    @ResponseStatus(HttpStatus.OK)
    public CartDTO getById(@PathVariable final Long cartId) {
        return cartService.getById(cartId);
    }
}
