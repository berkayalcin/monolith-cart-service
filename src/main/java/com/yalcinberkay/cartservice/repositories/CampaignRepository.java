package com.yalcinberkay.cartservice.repositories;

import com.yalcinberkay.cartservice.entities.Campaign;
import com.yalcinberkay.cartservice.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CampaignRepository extends JpaRepository<Campaign, String> {
    Optional<Campaign> findByCategoryIdAndStatus(final Long categoryId, final Status status);
}
