package com.epam.apartmentbooking.dao.impl.criteria;

import com.epam.apartmentbooking.dao.impl.criteria.primitive.ListStringCriteria;
import com.epam.apartmentbooking.dao.impl.criteria.primitive.NumberCriteria;

import java.util.List;

/**
 * Created by Serge_Kozik on 4/12/2017.
 */
public class SpaceCriteria extends AbstractCriteria{



    private SpaceCriteria() {
        super();
    }

    public static SpaceCriteria build() {
        return new SpaceCriteria();
    }

    public SpaceCriteria specifyBeds(Number minValue, Number maxValue, String columnName) {
        if ((minValue.intValue()>0)||(maxValue.intValue()>0)) {
            this.registerCriteria(new NumberCriteria(minValue,maxValue,columnName));
        }
        return this;
    }

    public SpaceCriteria specifyAccomodates (Number minValue, Number maxValue, String columnName) {
        if ((minValue.intValue()>0)||(maxValue.intValue()>0)) {
            this.registerCriteria(new NumberCriteria(minValue,maxValue,columnName));
        }
        return this;
    }

    public SpaceCriteria specifyRoomType(List<String> piece, String columnName) {
        if ((piece!=null)&&(!piece.isEmpty())) {
            this.registerCriteria(new ListStringCriteria(piece,columnName));
        }
        return this;
    }

}
