package fr.vilth.sprintplanner.domain.dtos.release;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import fr.vilth.sprintplanner.SetupUnitTest;

public class ReleaseCreateDtoTest extends SetupUnitTest {

    @Test
    void should_construct() {
	ReleaseCreateDto tested = new ReleaseCreateDto(1, 1, 1, "test");
	assertNotNull(tested);
    }

    @Test
    void should_return_toString() {
	ReleaseCreateDto actual = new ReleaseCreateDto(1, 1, 1, "test");
	String expected = "{pi=1, sprint=1, week=1, versionNumber=v1.1.1.0, releaser=test}";
	assertEquals(expected, actual.toString());
    }
}
