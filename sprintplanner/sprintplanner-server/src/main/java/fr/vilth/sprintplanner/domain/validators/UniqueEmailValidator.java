package fr.vilth.sprintplanner.domain.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import fr.vilth.sprintplanner.api.services.MemberService;

/**
 * Validator for {@code UniqueEmail} constraint.
 * 
 * @author Thierry VILLEPREUX
 */
public class UniqueEmailValidator
	implements ConstraintValidator<UniqueEmail, String> {

    private final MemberService service;

    /**
     * Creates a {@code UniqueEmailValidator} with given injected
     * {@code MemberService}.
     *
     * @param service an injected {@code MemberService}
     */
    protected UniqueEmailValidator(MemberService service) {
	this.service = service;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
	if (null == email || email.isEmpty()) { // Ignored
	    return true;
	}
	/*
	 * As existsByEmail(email) returns true if the email already exists, we
	 * return the opposite of existsByEmail(email) to assert that the given
	 * email is unique
	 */
	return !service.existsByEmail(email);
    }
}
