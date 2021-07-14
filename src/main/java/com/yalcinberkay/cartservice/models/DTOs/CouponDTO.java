package com.yalcinberkay.cartservice.models.DTOs;

import com.yalcinberkay.cartservice.enums.DiscountType;
import com.yalcinberkay.cartservice.enums.Status;
import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder
public class CouponDTO {
    Long id;
    Double amount;
    Double minimumCartAmount;
    String code;
    DiscountType discountType;
    Date expiresAt;
    Status status = Status.ACTIVE;
}
