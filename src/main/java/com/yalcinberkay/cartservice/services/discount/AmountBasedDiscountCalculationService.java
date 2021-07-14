package com.yalcinberkay.cartservice.services.discount;

public class AmountBasedDiscountCalculationService implements DiscountCalculationService {

    @Override
    public Double calculate(final Double discountAmount, final Double amount) {
        return discountAmount;
    }
}
