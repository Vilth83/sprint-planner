package fr.vilth.sprintplanner.domain.dtos.project;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import fr.vilth.sprintplanner.SetupUnitTest;

public class ProjectCreateDtoTest extends SetupUnitTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/projectCreation.csv", delimiter = ';')
    void should_return_toString(String json) {
	ProjectCreateDto actual = jsonConvert(json, ProjectCreateDto.class);
	String expected = "{trigram=SPL, name=Sprintplanner, email=sprint@planner, piDuration="
		+ "5, sprintDuration=2" + ", githubUser=vilth83, githubRepo="
		+ "sprintplanner" + "}";
	assertEquals(expected, actual.toString());
    }
}
