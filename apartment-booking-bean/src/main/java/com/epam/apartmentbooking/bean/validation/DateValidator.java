package com.epam.apartmentbooking.bean.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Serge_Kozik on 6/2/2017.
 */
public class DateValidator implements ConstraintValidator<DateConstraint,String> {
    private DateFormat dateFormat;

    @Override
    public void initialize(DateConstraint dateConstraint) {
        this.dateFormat = new SimpleDateFormat("dd-MM-YYYY");

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            dateFormat.parse(s);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
