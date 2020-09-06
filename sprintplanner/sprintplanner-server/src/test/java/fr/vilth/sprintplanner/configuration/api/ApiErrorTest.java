package fr.vilth.sprintplanner.configuration.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import fr.vilth.sprintplanner.SetupUnitTest;

/**
 * Test against ApiError
 * 
 * @author Thierry VILLEPREUX
 */
public class ApiErrorTest extends SetupUnitTest {

    @Test
    void should_construct() {
	ApiError tested = new ApiError("404", "Not Found");
	assertNotNull(tested);
    }

    @Test
    void should_return_toString() {
	ApiError actual = new ApiError("404", "Not Found");
	String expected = "{field=404, defaultMessage=Not Found}";
	assertEquals(expected, actual.toString());
    }
}
