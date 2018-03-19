package com.epam.apartmentbooking.dao.impl.criteria.primitive;

/**
 * Created by Serge_Kozik on 4/12/2017.
 */
public class BooleanCriteria implements CriteriaInterface {

    private boolean intendedFlag;
    private String columnName;

    public BooleanCriteria(boolean intendedFlag, String columnName) {
        this.intendedFlag = intendedFlag;
        this.columnName = columnName;
    }

    @Override
    public String generateQueryClause() {
        if (intendedFlag) {
            return columnName+"=1";
        }
        return columnName+"=0";
    }


}
