package com.yalcinberkay.cartservice.controllers;

import com.yalcinberkay.cartservice.models.DTOs.CampaignDTO;
import com.yalcinberkay.cartservice.services.CampaignService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campaigns")
@RequiredArgsConstructor
public class CampaignController {
    private final CampaignService campaignService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "get campaigns")
    public List<CampaignDTO> getAll() {
        return campaignService.getAll();
    }

    @GetMapping("{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "get campaign by category id")
    public CampaignDTO getByCategoryId(@PathVariable("categoryId") final Long categoryId) {
        return campaignService.getByCategoryId(categoryId);
    }
}
