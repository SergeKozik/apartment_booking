package com.epam.apartmentbooking.dao.impl.criteria;

import com.epam.apartmentbooking.dao.impl.criteria.primitive.NumberCriteria;

/**
 * Created by Serge_Kozik on 4/12/2017.
 */
public class AvailabilityCriteria extends AbstractCriteria{


    private AvailabilityCriteria() {
        super();
    }

    public static AvailabilityCriteria build() {
        return new AvailabilityCriteria();
    }

    public AvailabilityCriteria specifyMinNights(Number minValue, Number maxValue,String columnName) {
        if ((minValue.intValue()>0)||(maxValue.intValue()>0)) {
            this.registerCriteria(new NumberCriteria(minValue,maxValue,columnName));
        }
        return this;
    }
}
