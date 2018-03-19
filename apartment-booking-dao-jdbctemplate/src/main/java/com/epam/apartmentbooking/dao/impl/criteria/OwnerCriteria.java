package com.epam.apartmentbooking.dao.impl.criteria;

import com.epam.apartmentbooking.dao.impl.criteria.primitive.StringCriteria;

/**
 * Created by Serge_Kozik on 4/12/2017.
 */
public class OwnerCriteria extends AbstractCriteria {


    private OwnerCriteria() {
        super();
    }

    public static OwnerCriteria build() {
        return new OwnerCriteria();
    }

    public OwnerCriteria specifyTitle( String piece, String columnName) {
        this.registerCriteria(new StringCriteria(piece,columnName));
        return this;
    }

    public OwnerCriteria specifyAddress( String piece, String columnName) {
        this.registerCriteria(new StringCriteria(piece,columnName));
        return this;
    }
}
