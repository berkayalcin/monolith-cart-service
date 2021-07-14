package com.yalcinberkay.cartservice.models.DTOs;

import com.yalcinberkay.cartservice.enums.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter
public class CartDTO {
    private Long id;
    private Date createdAt;
    private Date lastModifiedAt;
    private Double amount;
    private Double totalDiscount;
    private Double totalCampaignDiscount;
    private Double totalCouponDiscount;
    private Double deliveryCost;
    private Status status;
    private List<CartItemDTO> items;
}
