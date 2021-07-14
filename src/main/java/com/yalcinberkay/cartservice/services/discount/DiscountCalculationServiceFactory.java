package com.yalcinberkay.cartservice.services.discount;

import com.yalcinberkay.cartservice.enums.DiscountType;
import com.yalcinberkay.cartservice.exceptions.BusinessException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DiscountCalculationServiceFactory {
    public DiscountCalculationService create(final DiscountType discountType) {
        if (discountType.equals(DiscountType.RATE)) {
            return new RateBasedDiscountCalculationService();
        }
        if (discountType.equals(DiscountType.AMOUNT)) {
            return new AmountBasedDiscountCalculationService();
        }

        throw new BusinessException("invalid.enum");
    }
}
