package fr.vilth.sprintplanner.domain.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import fr.vilth.sprintplanner.api.services.TaskService;

public class IsNotManagerValidator
	implements ConstraintValidator<IsNotManager, Long> {

    private final TaskService taskService;

    public IsNotManagerValidator(TaskService taskService) {
	this.taskService = taskService;
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
	if (id == null) {
	    return true;
	}
	boolean valid = !taskService.existByManagerId(id);
	return valid;
    }
}
