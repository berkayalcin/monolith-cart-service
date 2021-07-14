package com.yalcinberkay.cartservice.models.requests;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalculateCampaignDiscountRequest {
    private Long productId;
    private Integer quantity;
}
