package com.yalcinberkay.cartservice.services;

import com.yalcinberkay.cartservice.converters.CampaignEntityToDTOConverter;
import com.yalcinberkay.cartservice.enums.Status;
import com.yalcinberkay.cartservice.models.DTOs.CampaignDTO;
import com.yalcinberkay.cartservice.repositories.CampaignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampaignService {
    private final CampaignRepository campaignRepository;
    private final CampaignEntityToDTOConverter campaignEntityToDTOConverter;

    public List<CampaignDTO> getAll() {
        final var campaigns = campaignRepository.findAll();
        return campaigns.stream().map(campaignEntityToDTOConverter).collect(Collectors.toList());
    }

    public CampaignDTO getByCategoryId(final Long categoryId) {
        final var campaign = campaignRepository.findByCategoryIdAndStatus(categoryId, Status.ACTIVE);
        if (campaign.isEmpty()) {
            return null;
        }
        return campaignEntityToDTOConverter.apply(campaign.get());
    }
}
