package fr.vilth.sprintplanner.commons.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import fr.vilth.sprintplanner.commons.exceptions.ResourceNotFoundException;

/**
 * Custom controller advice to handle all {@code RestController} exceptions.
 * <p>
 * Manages handlers for exceptions to mutualize and standardize exception
 * handling for all {@code RestControllers}
 * 
 * @author Thierry VILLEPREUX
 */
@ControllerAdvice
public class CustomControllerAdvice {

    /**
     * Handles {@code ResourcesNotFound} exception.
     */
    @ExceptionHandler(value = ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Ressource has not been found")
    public void handleResourceNotfoundException() {
	// behavior is handled by annotations.
    }

    /**
     * Handles {@code DataIntegrityViolationException} exception.
     */
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "database issue, action cannot be done")
    public void handleDataIntegrityViolationException() {
	// behavior is handled by annotations.
    }

    /**
     * Handles {@code MethodArgumentNotValidException}.
     * <p>
     * Encapsulate all field errors and their message in {@code ApiError} and
     * returns the {@code List} of {@code ApiError}.
     * 
     * @param exception the intercepted exception
     * @return a {@code Lis} of {@code ApiError}
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<List<ApiError>> handleValidationError(
	    MethodArgumentNotValidException exception) {
	List<FieldError> errors = exception.getBindingResult().getFieldErrors();
	List<ApiError> apiErrors = errors.stream()
		.map(error -> new ApiError(error.getField(),
			error.getDefaultMessage()))
		.collect(Collectors.toList());
	return new ResponseEntity<>(apiErrors, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
