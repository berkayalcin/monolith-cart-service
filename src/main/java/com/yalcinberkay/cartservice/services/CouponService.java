package com.yalcinberkay.cartservice.services;

import com.yalcinberkay.cartservice.converters.CouponEntityToDTOConverter;
import com.yalcinberkay.cartservice.exceptions.BusinessException;
import com.yalcinberkay.cartservice.models.DTOs.CouponDTO;
import com.yalcinberkay.cartservice.repositories.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;
    private final CouponEntityToDTOConverter couponEntityToDTOConverter;

    public CouponDTO getByCode(final String code) {
        final var coupon = couponRepository.findByCode(code);
        if (coupon.isEmpty()) {
            throw new BusinessException("coupon.is.not.exists");
        }
        return couponEntityToDTOConverter.apply(coupon.get());
    }
}
