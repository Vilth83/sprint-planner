package fr.vilth.sprintplanner.domain.dtos.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import fr.vilth.sprintplanner.SetupUnitTest;

/**
 * Test upon {@code TaskViewDto}
 * 
 * @author Thierry VILLEPREUX
 */
public class TaskViewDtoTest extends SetupUnitTest {

    @Test
    void should_construct() {
	TaskViewDto actual = new TaskViewDto();
	assertNotNull(actual);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/taskCreation.csv", delimiter = ';')
    void should_return_toString(String json) {
	TaskViewDto task = jsonConvert(json, TaskViewDto.class);
	String expected = "{name=release, description=release test, email=release@mail, manager=null}";
	String actual = task.toString();
	assertEquals(expected, actual);
    }
}
