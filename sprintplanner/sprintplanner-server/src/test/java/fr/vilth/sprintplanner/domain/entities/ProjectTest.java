package fr.vilth.sprintplanner.domain.entities;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import fr.vilth.sprintplanner.SetupUnitTest;

public class ProjectTest extends SetupUnitTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/projectCreation.csv", delimiter = ';')
    void should_return_toString(String json) {
	Project actual = jsonConvert(json, Project.class);
	String expected = "{name=Sprintplanner, email=sprint@planner, piDuration="
		+ "5, sprintDuration=2" + ", githubUser=vilth83, githubRepo="
		+ "sprintplanner" + "}";
	assertEquals(expected, actual.toString());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/projectCreation.csv", delimiter = ';')
    void should_return_piDuration(String json) {
	Project actual = jsonConvert(json, Project.class);
	assertEquals(5, actual.getPiDuration());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/projectCreation.csv", delimiter = ';')
    void should_return_sprintDuration(String json) {
	Project actual = jsonConvert(json, Project.class);
	assertEquals(2, actual.getSprintDuration());
    }
}
