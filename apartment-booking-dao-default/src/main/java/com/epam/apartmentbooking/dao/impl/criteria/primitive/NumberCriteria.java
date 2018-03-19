package com.epam.apartmentbooking.dao.impl.criteria.primitive;

/**
 * Created by Serge_Kozik on 4/12/2017.
 */
public class NumberCriteria implements CriteriaInterface{
    private Number minValue;
    private Number maxValue;
    private String columnName;

    public NumberCriteria(Number minValue, Number maxValue, String columnName) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.columnName = columnName;
    }

    public String generateQueryClause() {
        if (maxValue.doubleValue()>minValue.doubleValue()) {
            return columnName+">="+String.valueOf(minValue)+" AND "+columnName+"<="+String.valueOf(maxValue);
        } else {
            return columnName+">="+String.valueOf(minValue);
        }
    }

}
