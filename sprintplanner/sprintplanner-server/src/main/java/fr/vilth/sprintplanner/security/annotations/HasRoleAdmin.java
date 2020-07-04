package fr.vilth.sprintplanner.security.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Custom annotation to manage <code>@PreAuthorized</code>
 * {@code ROLE.ROLE_ADMIN}.
 * <p>
 * 
 * @author Thierry VILLEPREUX
 */
@PreAuthorize("hasRole('ROLE_ADMIN')")
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface HasRoleAdmin {
    // no action, encapsulate PreAuthorized roles for readibility
}
