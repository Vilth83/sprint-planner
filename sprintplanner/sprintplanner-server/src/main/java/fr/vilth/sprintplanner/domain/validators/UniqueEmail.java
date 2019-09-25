package fr.vilth.sprintplanner.domain.validators;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.TYPE })
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface UniqueEmail {

    String message() default "{E_UNIQUE_EMAIL}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
