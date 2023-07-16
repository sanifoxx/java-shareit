package ru.practicum.shereit.booking.model.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidBookingTimeValidator.class)
public @interface ValidBookingTime {

    String message() default "Incorrect start or end DataTime";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
