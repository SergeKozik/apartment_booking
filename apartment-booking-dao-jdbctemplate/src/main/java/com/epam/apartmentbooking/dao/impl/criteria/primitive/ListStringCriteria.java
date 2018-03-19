package com.epam.apartmentbooking.dao.impl.criteria.primitive;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serge_Kozik on 5/2/2017.
 */
public class ListStringCriteria implements CriteriaInterface {

    private List<String> stringMasks;
    private String columnName;

    public ListStringCriteria(List<String> stringMasks, String columnName) {

        this.stringMasks = new ArrayList<>();
        for (String mask:stringMasks) {
            if (!mask.trim().isEmpty()) {
                this.stringMasks.add("%"+mask
                        .replace("!", "!!")
                        .replace("%", "!%")
                        .replace("[", "![") + "%");
            }
        }
        this.columnName = columnName;
    }

    @Override
    public String generateQueryClause() {
        String result="";
        if (!stringMasks.isEmpty()) {
            result="("+columnName+" LIKE '"+stringMasks.get(0)+"'";
            for (int ii=1; ii<this.stringMasks.size(); ii++) {
                result = result + " AND " + columnName+" LIKE '"+stringMasks.get(ii)+"'";
            }
            result = result + ")";
        }
        return result;
    }
}
