package com.epam.apartmentbooking.entity.jpaconverter;

import org.javamoney.moneta.Money;

import javax.money.MonetaryAmount;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.math.BigDecimal;

/**
 * Created by Serge_Kozik on 7/10/2017.
 */
@Converter
public class MoneyToDecimal implements AttributeConverter<MonetaryAmount,BigDecimal> {
    @Override
    public BigDecimal convertToDatabaseColumn(MonetaryAmount monetaryAmount) {
        if ((monetaryAmount==null)||(monetaryAmount.isZero())) {
            return BigDecimal.ZERO;
        }
        Money money = Money.from(monetaryAmount);
        return money.getNumberStripped();
    }

    @Override
    public MonetaryAmount convertToEntityAttribute(BigDecimal bigDecimal) {
        if ((bigDecimal==null)||(bigDecimal.compareTo(BigDecimal.ZERO)==0)) {
            return Money.of(0,"USD");
        }
        return Money.of(bigDecimal,"USD");
    }
}
