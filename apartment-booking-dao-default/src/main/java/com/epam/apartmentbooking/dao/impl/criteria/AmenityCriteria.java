package com.epam.apartmentbooking.dao.impl.criteria;

import com.epam.apartmentbooking.dao.impl.criteria.primitive.BooleanCriteria;

/**
 * Created by Serge_Kozik on 4/12/2017.
 */
public class AmenityCriteria extends AbstractCriteria {

    private AmenityCriteria() {
        super();
    }

    public static AmenityCriteria build() {
        return new AmenityCriteria();
    }

    public AmenityCriteria specifyWifi(boolean flag, String columnName) {
        if (flag) {
            this.registerCriteria(new BooleanCriteria(flag,columnName));
        }
        return this;
    }

    public AmenityCriteria specifyFreeParking(boolean flag, String columnName) {
        if (flag) {
            this.registerCriteria(new BooleanCriteria(flag,columnName));
        }
        return this;
    }

}
