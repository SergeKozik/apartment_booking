package com.epam.apartmentbooking.dao.impl.criteria.primitive;

/**
 * Created by Serge_Kozik on 4/12/2017.
 */
public class StringCriteria implements CriteriaInterface{

    private String mask;
    private String columnName;

    public StringCriteria(String mask, String columnName) {
        this.mask = "%"+mask
                .replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_")
                .replace("[", "![") + "%";
        this.columnName = columnName;
    }

    @Override
    public String generateQueryClause() {
        return columnName+" LIKE '"+mask+"'";
    }


}
