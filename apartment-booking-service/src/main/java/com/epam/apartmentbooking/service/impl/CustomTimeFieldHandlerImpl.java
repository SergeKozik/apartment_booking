package com.epam.apartmentbooking.service.impl;

import com.epam.apartmentbooking.service.CustomTimeFieldHandler;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Roldo on 18.05.2017.
 */
@Component
public class CustomTimeFieldHandlerImpl implements CustomTimeFieldHandler {
    private static final String DATE_FORMAT="dd-MM-YYYY";

    @Override
    public String showCurrentDateString() {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        dateFormat.setTimeZone(TimeZone.getDefault());
        return dateFormat.format(new Date(System.currentTimeMillis()));
    }
}
