package fr.vilth.sprintplanner.commons.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import fr.vilth.sprintplanner.commons.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Ressource has not been found")
    public void handleResourceNotfoundException() {
	//
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<List<ApiError>> handleValidationError(
	    MethodArgumentNotValidException exception) {
	List<FieldError> errors = exception.getBindingResult().getFieldErrors();
	List<ApiError> apiErrors = errors.stream()
		.map(error -> new ApiError(error.getField(),
			error.getDefaultMessage()))
		.collect(Collectors.toList());
	return new ResponseEntity<List<ApiError>>(apiErrors,
		HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
