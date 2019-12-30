package fr.vilth.sprintplanner;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Set up class for unit tests.
 * <p>
 * Redefines an {@code ObjectMapper} to create objects from JSON and ease tests.
 * 
 * @author Thierry VILLEPREUX
 */
public class SetupUnitTest {

    protected static final ObjectMapper MAPPER = new ObjectMapper();

    protected SetupUnitTest() {
	//
    }

    @BeforeAll
    protected static void setUp() {
	MAPPER.setVisibility(
		MAPPER.getSerializationConfig().getDefaultVisibilityChecker()
			.withFieldVisibility(JsonAutoDetect.Visibility.ANY)
			.withGetterVisibility(JsonAutoDetect.Visibility.NONE)
			.withIsGetterVisibility(JsonAutoDetect.Visibility.NONE)
			.withSetterVisibility(JsonAutoDetect.Visibility.NONE));
	MAPPER.registerModule(new JavaTimeModule());
	MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    protected final <D> D jsonConvert(String inputs, Class<D> destinationType) {
	D converted = null;
	try {
	    converted = MAPPER.readValue(inputs, destinationType);
	} catch (IOException ex) {
	    throw new IllegalArgumentException(
		    "wrong json format in csv source file", ex);
	}
	return converted;
    }
}
