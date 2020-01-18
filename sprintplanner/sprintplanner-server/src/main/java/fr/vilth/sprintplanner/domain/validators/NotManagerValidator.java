package fr.vilth.sprintplanner.domain.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import fr.vilth.sprintplanner.api.services.TaskService;

/**
 * Validator for {@code NotManager} constraint.
 * 
 * @author Thierry VILLEPREUX
 */
public class NotManagerValidator
	implements ConstraintValidator<NotManager, Long> {

    private final TaskService taskService;

    /**
     * Creates a {@code NotManagerValidator} with given injected
     * {@code TaskService}.
     *
     * @param taskService an injected {@code TaskService}
     */
    public NotManagerValidator(TaskService taskService) {
	this.taskService = taskService;
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
	if (id == null) {
	    return true;
	}
	return !taskService.existByManagerId(id);
    }
}
