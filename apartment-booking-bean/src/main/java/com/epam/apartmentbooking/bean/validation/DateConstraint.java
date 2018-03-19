package com.epam.apartmentbooking.bean.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

/**
 * Created by Serge_Kozik on 6/2/2017.
 */
@NotNull
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateValidator.class)
@Documented
public @interface DateConstraint {
    String message() default "{message.error.date-error}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
