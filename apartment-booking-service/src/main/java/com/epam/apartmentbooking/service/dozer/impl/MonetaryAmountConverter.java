package com.epam.apartmentbooking.service.dozer.impl;

import org.dozer.DozerConverter;
import org.javamoney.moneta.Money;

import javax.money.MonetaryAmount;

/**
 * Created by Serge_Kozik on 4/18/2017.
 */
public class MonetaryAmountConverter extends DozerConverter<MonetaryAmount,MonetaryAmount> {

    public MonetaryAmountConverter() {
        super(MonetaryAmount.class, MonetaryAmount.class);
    }

    @Override
    public MonetaryAmount convertTo(MonetaryAmount monetaryAmount, MonetaryAmount monetaryAmount2) {
        if (monetaryAmount==null) {
            return Money.of(0,"USD");
        } else {
            return monetaryAmount;
        }
    }

    @Override
    public MonetaryAmount convertFrom(MonetaryAmount monetaryAmount, MonetaryAmount monetaryAmount2) {
        return this.convertTo(monetaryAmount,monetaryAmount2);
    }
}
