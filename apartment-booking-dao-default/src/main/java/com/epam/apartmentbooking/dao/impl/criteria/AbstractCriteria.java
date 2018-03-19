package com.epam.apartmentbooking.dao.impl.criteria;

import com.epam.apartmentbooking.dao.impl.criteria.primitive.CriteriaInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serge_Kozik on 4/12/2017.
 */
public abstract class AbstractCriteria {
    private List<CriteriaInterface> criteria;

    public AbstractCriteria() {
        criteria = new ArrayList<>();
    }

    protected void registerCriteria(CriteriaInterface newCriteria) {
        criteria.add(newCriteria);
    }

    public String returnSelectSQLQuery(String tableName, String[] returnColumns) {
        if (criteria.isEmpty()) {
            return "";
        }
        String stringColumns="*";
        if (returnColumns!=null) {
            stringColumns=returnColumns[0];
        }
        for (int ii=1; ii<returnColumns.length; ii++) {
            stringColumns=stringColumns+", "+returnColumns[ii];
        }
        String stringConditions="";
        if (!criteria.isEmpty()) {
            stringConditions=" WHERE "+criteria.get(0).generateQueryClause();
            for (int ii=1; ii<criteria.size(); ii++) {
                stringConditions=stringConditions+" AND "+criteria.get(ii).generateQueryClause();
            }
        }
        String result = "SELECT "+stringColumns+" FROM "+tableName+stringConditions;
        return result;

    }
}
