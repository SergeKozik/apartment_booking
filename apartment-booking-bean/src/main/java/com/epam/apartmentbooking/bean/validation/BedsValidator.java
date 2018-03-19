package com.epam.apartmentbooking.bean.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Serge_Kozik on 5/25/2017.
 */
public class BedsValidator implements ConstraintValidator<BedsConstraint,Integer> {
    @Override
    public void initialize(BedsConstraint bedsConstraint) {

    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return !((integer!=null)&&(integer<0));
    }
}
