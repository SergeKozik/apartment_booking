package com.epam.apartmentbooking.dao.impl.criteria;

import com.epam.apartmentbooking.dao.impl.criteria.primitive.MonetaryAmountCriteria;
import com.epam.apartmentbooking.dao.impl.criteria.primitive.NumberCriteria;

import java.math.BigDecimal;

/**
 * Created by Serge_Kozik on 4/12/2017.
 */
public class PriceCriteria extends AbstractCriteria {

    private PriceCriteria() {
        super();
    }

    public static PriceCriteria build() {
        return new PriceCriteria();
    }

    public PriceCriteria specifyDaily(BigDecimal minAmount, BigDecimal maxAmount, String columnName) {
        if ((!minAmount.isZero())||(!maxAmount.isZero())) {
            this.registerCriteria(new MonetaryAmountCriteria(minAmount, maxAmount, columnName));
        }
        return this;
    }

    public  PriceCriteria specifyWeeklyDiscount(Number minValue, Number maxValue, String columnName) {
        if ((minValue.doubleValue()>0)||(maxValue.doubleValue()>0)) {
            this.registerCriteria(new NumberCriteria(minValue, maxValue, columnName));
        }
        return this;
    }

    public PriceCriteria specifyMonthlyDiscount(Number minValue, Number maxValue, String columnName) {
        if ((minValue.doubleValue()>0)||(maxValue.doubleValue()>0)) {
            this.registerCriteria(new NumberCriteria(minValue, maxValue, columnName));
        }
        return this;
    }
}
