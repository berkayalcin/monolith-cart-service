package com.yalcinberkay.cartservice.converters;

import com.yalcinberkay.cartservice.entities.Coupon;
import com.yalcinberkay.cartservice.models.DTOs.CouponDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CouponEntityToDTOConverter implements Function<Coupon, CouponDTO> {

    @Override
    public CouponDTO apply(final Coupon coupon) {
        return CouponDTO.builder()
                .amount(coupon.getAmount())
                .id(coupon.getId())
                .discountType(coupon.getDiscountType())
                .code(coupon.getCode())
                .expiresAt(coupon.getExpiresAt())
                .minimumCartAmount(coupon.getMinimumCartAmount())
                .build();
    }
}
