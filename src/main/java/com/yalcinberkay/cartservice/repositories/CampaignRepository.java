package com.yalcinberkay.cartservice.repositories;

import com.yalcinberkay.cartservice.entities.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository extends JpaRepository<Campaign, String> {
}
