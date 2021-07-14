package com.yalcinberkay.cartservice.models.DTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CartItemDTO {
    private Long id;
    private Long productId;
    private Integer quantity;
    private Double amount;
    private Double unitPrice;
    private Double discount;
    private Long cartId;
    private ProductDTO product;
}
