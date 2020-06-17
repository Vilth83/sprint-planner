package fr.vilth.sprintplanner.commons.security.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Custom annotation to manage <code>@PreAuthorize</code> {@code Role.USER}.
 * <p>
 * Routes accessible to {@code ROLE.ROLE_USER} are de facto accessible to
 * {@code ROLE.ROLE_ADMIN}.
 * 
 * @author Thierry VILLEPREUX
 */
@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface HasRoleUser {
    // no action, encapsulate PreAuthorized roles for readibility
}
