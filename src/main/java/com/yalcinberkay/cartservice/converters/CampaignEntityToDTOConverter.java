package com.yalcinberkay.cartservice.converters;

import com.yalcinberkay.cartservice.entities.Campaign;
import com.yalcinberkay.cartservice.models.DTOs.CampaignDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CampaignEntityToDTOConverter implements Function<Campaign, CampaignDTO> {
    @Override
    public CampaignDTO apply(final Campaign campaign) {
        return CampaignDTO.builder()
                .amount(campaign.getAmount())
                .categoryId(campaign.getCategoryId())
                .id(campaign.getId())
                .discountType(campaign.getDiscountType())
                .expiresAt(campaign.getExpiresAt())
                .minimumCartItem(campaign.getMinimumCartItem())
                .status(campaign.getStatus())
                .build();
    }
}
