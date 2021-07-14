package com.yalcinberkay.cartservice.models.requests;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemAddRequest {
    private Long productId;
    private Integer quantity;
    private Long cartId;
}
