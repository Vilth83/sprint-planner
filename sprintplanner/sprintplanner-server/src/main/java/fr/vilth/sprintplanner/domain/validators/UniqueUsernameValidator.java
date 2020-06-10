package fr.vilth.sprintplanner.domain.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import fr.vilth.sprintplanner.api.services.CustomUserDetailsService;

/**
 * validator for {@code @UniqueUsername} constraint annotation.
 * 
 * @author Thierry VILLEPREUX
 *
 */
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

	private final CustomUserDetailsService service;

	protected UniqueUsernameValidator(CustomUserDetailsService userService) {
		service = userService;
	}

	@Override
	public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
		if (null == username || username.isEmpty()) {
			return true;
		}
		return service.usernameIsUnique(username);
	}
}
