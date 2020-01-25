package fr.vilth.sprintplanner.domain.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import fr.vilth.sprintplanner.api.services.CandidateService;

/**
 * Validator for {@code NotCandidate} constraint.
 * 
 * @author Thierry VILLEPREUX
 */
public class NotCandidateValidator
	implements ConstraintValidator<NotCandidate, Long> {

    private final CandidateService candidateService;

    /**
     * Creates a {@code NotCandidateValidator} with given injected
     * {@code CandidateService}.
     *
     * @param candidateService an injected {@code CandidateService}
     */
    public NotCandidateValidator(CandidateService candidateService) {
	this.candidateService = candidateService;
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
	if (id == null) {
	    return true;
	}
	return !candidateService.existByMemberId(id);
    }
}
