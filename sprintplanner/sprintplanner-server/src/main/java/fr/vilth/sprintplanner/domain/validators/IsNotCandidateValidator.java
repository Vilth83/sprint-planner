package fr.vilth.sprintplanner.domain.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import fr.vilth.sprintplanner.api.services.CandidateService;

public class IsNotCandidateValidator
	implements ConstraintValidator<IsNotCandidate, Long> {

    private final CandidateService candidateService;

    public IsNotCandidateValidator(CandidateService candidateService) {
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
