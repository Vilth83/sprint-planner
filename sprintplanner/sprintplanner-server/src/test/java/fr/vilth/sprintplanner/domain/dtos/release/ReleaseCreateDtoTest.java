package fr.vilth.sprintplanner.domain.dtos.release;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import fr.vilth.sprintplanner.SetupUnitTest;

public class ReleaseCreateDtoTest extends SetupUnitTest {

    @Test
    void should_construct() {
	ReleaseCreateDto tested = new ReleaseCreateDto(1, 1, 1, null);
	assertNotNull(tested);
    }

    @Test
    void should_return_toString() {
	ReleaseCreateDto actual = new ReleaseCreateDto(1, 1, 1, null);
	String expected = "{pi=1, sprint=1, week=1, assignee=null}";
	assertEquals(expected, actual.toString());
    }
}
