package com.epam.apartmentbooking.dao.impl.criteria.primitive;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;

/**
 * Created by Serge_Kozik on 4/12/2017.
 */
public class MonetaryAmountCriteria implements CriteriaInterface {

    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private String columnName;

    public MonetaryAmountCriteria(MonetaryAmount minAmount, MonetaryAmount maxAmount,  String columnName) {
        this.minAmount = minAmount.getNumber().numberValue(BigDecimal.class);
        this.maxAmount = maxAmount.getNumber().numberValue(BigDecimal.class);
        this.columnName=columnName;
    }

    @Override
    public String generateQueryClause() {
        return columnName+">="+String.valueOf(minAmount)+" AND "+columnName+"<="+String.valueOf(maxAmount);
    }


}
