package fr.vilth.sprintplanner.domain.dtos.project;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import fr.vilth.sprintplanner.SetupUnitTest;

/**
 * Unit test of ProjectViewDto
 * 
 * @author Thierry VILLEPREUX
 *
 */
public class ProjectViewDtoTest extends SetupUnitTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/projectCreation.csv", delimiter = ';')
	void should_return_toString(String json) {
		ProjectViewDto actual = jsonConvert(json, ProjectViewDto.class);
		String expected = "{trigram=SPL, name=Sprintplanner, email=sprint@planner, piDuration=" + "5, sprintDuration=2"
				+ ", githubUser=vilth83, githubRepo=" + "sprintplanner" + "}";
		assertEquals(expected, actual.toString());
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/projectCreation.csv", delimiter = ';')
	void should_return_getters(String json) {
		ProjectViewDto actual = jsonConvert(json, ProjectViewDto.class);
		Assertions.assertAll(() -> assertEquals(5, actual.getPiDuration()),
				() -> assertEquals(2, actual.getSprintDuration()),
				() -> assertEquals("sprint@planner", actual.getEmail()));
	}
}
