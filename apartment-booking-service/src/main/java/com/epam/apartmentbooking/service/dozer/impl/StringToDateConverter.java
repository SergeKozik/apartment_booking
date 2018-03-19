package com.epam.apartmentbooking.service.dozer.impl;

import org.dozer.DozerConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Serge_Kozik on 5/11/2017.
 */
public class StringToDateConverter extends DozerConverter<String,Date> {

    public StringToDateConverter() {
        super(String.class, Date.class);
    }

    @Override
    public Date convertTo(String s, Date date) {
        Date result = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        dateFormat.setTimeZone(TimeZone.getDefault());
        try {
            result = dateFormat.parse(s);
        } catch (ParseException e) {
            return null;
        }
        return result;
    }

    @Override
    public String convertFrom(Date date, String s) {
        if (date==null) {
            return "--";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        dateFormat.setTimeZone(TimeZone.getDefault());
        return dateFormat.format(date);
    }
}
