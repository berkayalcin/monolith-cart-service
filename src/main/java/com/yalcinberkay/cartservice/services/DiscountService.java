package com.yalcinberkay.cartservice.services;

import com.yalcinberkay.cartservice.models.requests.ApplyCouponDiscountRequest;
import com.yalcinberkay.cartservice.models.requests.CalculateCampaignDiscountRequest;
import com.yalcinberkay.cartservice.services.discount.DiscountCalculationServiceFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DiscountService {
    private final CouponService couponService;
    private final ProductService productService;
    private final CampaignService campaignService;
    private final CategoryService categoryService;
    private final CartService cartService;

    DiscountService(final CouponService couponService,
                    final ProductService productService,
                    final CampaignService campaignService,
                    final CategoryService categoryService,
                    @Lazy final CartService cartService) {

        this.couponService = couponService;
        this.productService = productService;
        this.campaignService = campaignService;
        this.categoryService = categoryService;
        this.cartService = cartService;
    }

    public Double calculateCampaignDiscount(final CalculateCampaignDiscountRequest calculateCampaignDiscountRequest) {
        final var product = productService.getById(calculateCampaignDiscountRequest.getProductId());
        final var category = categoryService.getById(product.getCategoryId());
        final var campaign = campaignService.getByCategoryId(category.getId());

        if (Objects.isNull(campaign)) {
            return 0.0;
        }

        if (campaign.getMinimumCartItem() > calculateCampaignDiscountRequest.getQuantity()) {
            return 0.0;
        }

        final var discountCalculationService = DiscountCalculationServiceFactory.create(campaign.getDiscountType());
        return discountCalculationService.calculate(campaign.getAmount(), product.getPrice());
    }

    public Double calculateCouponDiscount(final ApplyCouponDiscountRequest applyCouponDiscountRequest) {
        final var coupon = couponService.getByCode(applyCouponDiscountRequest.getCouponCode());
        final var cart = cartService.getById(applyCouponDiscountRequest.getCartId());
        if (cart.getAmount() < coupon.getMinimumCartAmount()) {
            return 0.0;
        }
        final var discountCalculationService = DiscountCalculationServiceFactory.create(coupon.getDiscountType());
        return discountCalculationService.calculate(coupon.getAmount(), cart.getAmount());
    }
}
