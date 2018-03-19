package com.epam.apartmentbooking.bean.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

/**
 * Created by Serge_Kozik on 5/25/2017.
 */
@NotNull
@Min(value = 0)
@Digits(fraction = 0, integer = 100)
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { })
@Documented
public @interface GuestsConstraint {
    String message() default "{message.error.guests-error}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
