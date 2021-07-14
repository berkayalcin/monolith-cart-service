package com.yalcinberkay.cartservice.services.discount;

public interface DiscountCalculationService {
    Double calculate(final Double discountAmount, final Double amount);
}

