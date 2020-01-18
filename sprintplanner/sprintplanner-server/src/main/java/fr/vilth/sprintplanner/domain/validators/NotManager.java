package fr.vilth.sprintplanner.domain.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Validation annotation to ensure that a {@code Member} is not manager of a
 * {@code Task}.
 * 
 * @author Thierry VILLEPREUX
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.TYPE })
@Constraint(validatedBy = NotManagerValidator.class)
public @interface NotManager {

    /**
     * Message to be returned when violating the constraint.
     * 
     * @return a message depending on the locale.
     */
    String message() default "{E_UNDELETABLE_MANAGER}";

    /**
     * An array constraints the validation belongs to.
     * 
     * @return an array of constraints.
     */
    Class<?>[] groups() default {};

    /**
     * Give informations about the constraint violation.
     * 
     * @return an array of objects.
     */
    Class<? extends Payload>[] payload() default {};
}
