package fr.vilth.sprintplanner.domain.dtos.job;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import fr.vilth.sprintplanner.SetupUnitTest;

public class JobUpdateDtoTest extends SetupUnitTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/JobCreation.csv", delimiter = ';')
    void should_return_getters(String json) {
	JobUpdateDto actual = jsonConvert(json, JobUpdateDto.class);
	Assertions.assertAll(
		() -> assertEquals("releaser", actual.getTask()),
		() -> assertEquals("job", actual.getTitle()),
		() -> Assertions.assertTrue(actual.isActive()));
    }
}
