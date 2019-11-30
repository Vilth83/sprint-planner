package fr.vilth.sprintplanner.api;

import java.io.IOException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Setup class to be implemented by integration test classes.
 * <p>
 * provides convenient json and dto converters and a validation test method.
 * 
 * @author Thierry VILLEPREUX
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public abstract class SetupIntTest {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private LocalValidatorFactoryBean validatorFactory;

	protected final <D> D jsonConvert(String inputs, Class<D> destinationType) {
		D converted = null;
		try {
			converted = objectMapper.readValue(inputs, destinationType);
		} catch (IOException ex) {
			throw new IllegalArgumentException("wrong json format in csv source file", ex);
		}
		return converted;
	}

	protected final <D> D dtoConvert(D inputs, Class<D> destinationType) {
		return modelMapper.map(inputs, destinationType);
	}

	protected final <D> boolean isValid(D inputs) {
		Validator validator = validatorFactory.getValidator();
		Set<ConstraintViolation<D>> violations = validator.validate(inputs);
		return violations.isEmpty();
	}
}
