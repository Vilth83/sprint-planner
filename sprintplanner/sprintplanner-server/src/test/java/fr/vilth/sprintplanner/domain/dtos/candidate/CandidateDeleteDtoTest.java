package fr.vilth.sprintplanner.domain.dtos.candidate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import fr.vilth.sprintplanner.SetupUnitTest;

/**
 * Test on CandidateDeleteDto.
 * 
 * @author Thierry VILLEPREUX
 *
 */
public class CandidateDeleteDtoTest extends SetupUnitTest {

	@Test
	void should_construct() {
		CandidateDeleteDto actual = new CandidateDeleteDto();
		assertNotNull(actual);
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/candidateDelete.csv", delimiter = ';')
	void should_return_toString(String json) {
		CandidateDeleteDto candidate = jsonConvert(json, CandidateDeleteDto.class);
		String expected = "{id=-3}";
		String actual = candidate.toString();
		assertEquals(expected, actual);
	}
}
