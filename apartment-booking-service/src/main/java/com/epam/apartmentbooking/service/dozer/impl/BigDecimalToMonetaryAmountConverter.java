package com.epam.apartmentbooking.service.dozer.impl;

import org.dozer.DozerConverter;
import org.javamoney.moneta.Money;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

/**
 * Created by Serge_Kozik on 5/11/2017.
 */
public class BigDecimalToMonetaryAmountConverter extends DozerConverter<BigDecimal,MonetaryAmount> {

    public BigDecimalToMonetaryAmountConverter() {
        super(BigDecimal.class, MonetaryAmount.class);
    }

    @Override
    public MonetaryAmount convertTo(BigDecimal bigDecimal, MonetaryAmount monetaryAmount) {
        if ((bigDecimal==null)||(bigDecimal.compareTo(BigDecimal.ZERO)==0)) {
            return Money.of(0,"USD");
        }
        return Money.of(bigDecimal,"USD");
    }

    @Override
    public BigDecimal convertFrom(MonetaryAmount monetaryAmount, BigDecimal bigDecimal) {
        if ((monetaryAmount==null)||(monetaryAmount.isZero())) {
            return BigDecimal.ZERO;
        }
        Money money = Money.from(monetaryAmount);
        return money.getNumberStripped();
    }
}
