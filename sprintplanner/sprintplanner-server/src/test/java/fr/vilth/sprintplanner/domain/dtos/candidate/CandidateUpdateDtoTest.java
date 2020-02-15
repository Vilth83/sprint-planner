package fr.vilth.sprintplanner.domain.dtos.candidate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import fr.vilth.sprintplanner.SetupUnitTest;

public class CandidateUpdateDtoTest extends SetupUnitTest {

    @Test
    void should_construct() {
	CandidateUpdateDto actual = new CandidateUpdateDto();
	assertNotNull(actual);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/candidateUpdate.csv", delimiter = ';')
    void should_return_toString(String json) {
	CandidateUpdateDto candidate = jsonConvert(json,
		CandidateUpdateDto.class);
	String expected = "{id=1, priority=10, status=AVAILABLE}";
	String actual = candidate.toString();
	assertEquals(expected, actual);
    }
}
