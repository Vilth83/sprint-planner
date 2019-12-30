package fr.vilth.sprintplanner.domain.dtos.candidate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import fr.vilth.sprintplanner.SetupUnitTest;

/**
 * tests upon {@code CandidateViewDto}
 * 
 * @author Thierry VILLEPREUX
 */
public class CandidateViewDtoTest extends SetupUnitTest {

    @Test
    void should_construct() {
	CandidateViewDto actual = new CandidateViewDto();
	assertNotNull(actual);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/candidateView.csv", delimiter = ';')
    void should_return_toString(String json) {
	CandidateViewDto candidate = jsonConvert(json, CandidateViewDto.class);
	String expected = "{member=null, status=AVAILABLE, task=null, priority=1}";
	String actual = candidate.toString();
	assertEquals(expected, actual);
    }
}
