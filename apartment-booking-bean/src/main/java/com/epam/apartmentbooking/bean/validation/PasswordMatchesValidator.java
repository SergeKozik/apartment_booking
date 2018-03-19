package com.epam.apartmentbooking.bean.validation;


import com.epam.apartmentbooking.bean.UserRegisterBean;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Serge on 11.02.2017.
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches,Object> {

    public void initialize(PasswordMatches passwordMatches) {

    }

    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        try {
            UserRegisterBean user = (UserRegisterBean) o;
            return user.getPassword().equals(user.getPasswordConfirm());
        } catch (ClassCastException e) {
            return false;
        }
    }
}
