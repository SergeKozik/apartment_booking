package com.epam.apartmentbooking.bean.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by Serge on 11.02.2017.
 */
@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Documented
public @interface PasswordMatches {
    String message() default "{message.error.password-match}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
