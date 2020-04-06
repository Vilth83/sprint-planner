package fr.vilth.sprintplanner.domain.dtos.candidate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import fr.vilth.sprintplanner.SetupUnitTest;

/**
 * Tests upon {@code CandidateCreateDto}.
 * 
 * @author Thierry VILLEPREUX
 */
public class CandidateCreateDtoTest extends SetupUnitTest {

    @Test
    void should_construct() {
	CandidateCreateDto actual = new CandidateCreateDto();
	assertNotNull(actual);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/candidateCreation.csv", delimiter = ';')
    void should_return_toString(String json) {
	CandidateCreateDto candidate = jsonConvert(json,
		CandidateCreateDto.class);
	String expected = "{member={id=-1}, task={id=-4}, priority=1, status=AVAILABLE}";
	String actual = candidate.toString();
	assertEquals(expected, actual);
    }
}
