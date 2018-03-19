package com.epam.apartmentbooking.dao.impl.criteria;

import com.epam.apartmentbooking.dao.impl.criteria.primitive.DateCriteria;

import java.util.Date;

/**
 * Created by Serge_Kozik on 4/13/2017.
 */
public class BookingCriteria extends AbstractCriteria {

    private BookingCriteria() {
        super();
    }

    public static BookingCriteria build() {
        return new BookingCriteria();
    }

    public BookingCriteria specifyPeriod(Date checkIn, Date checkOut, String columnNameIn, String columnNameOut) {
        this.registerCriteria(new DateCriteria(checkIn,checkOut,columnNameIn,columnNameOut));
        return this;
    }
}
