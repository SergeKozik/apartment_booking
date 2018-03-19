package com.epam.apartmentbooking.bean.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by Serge_Kozik on 5/25/2017.
 */
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BedsValidator.class)
@Documented
public @interface BedsConstraint {
    String message() default "{message.error.beds-error}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
