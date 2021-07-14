package com.yalcinberkay.cartservice.controllers;

import com.yalcinberkay.cartservice.models.DTOs.CouponDTO;
import com.yalcinberkay.cartservice.services.CouponService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coupons")
@RequiredArgsConstructor
public class CouponController {
    private final CouponService couponService;

    @GetMapping("{code}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("gets coupon by code")
    public CouponDTO getByCode(@PathVariable("code") final String code) {
        return couponService.getByCode(code);
    }
}
