package com.yalcinberkay.cartservice.models.requests;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplyCouponDiscountRequest {
    private Long cartId;
    private String couponCode;
}
