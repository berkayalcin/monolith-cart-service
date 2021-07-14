package com.yalcinberkay.cartservice.services.discount;

public class RateBasedDiscountCalculationService implements DiscountCalculationService {
    @Override
    public Double calculate(final Double discountAmount, final Double amount) {
        final var totalDiscount = (amount / 100) * discountAmount;
        if (totalDiscount > amount) {
            return amount;
        }
        return (amount / 100 * discountAmount);
    }
}
