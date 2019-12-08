package fr.vilth.sprintplanner.domain.dtos.candidate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

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

    CandidateCreateDtoTest() throws IOException {
	//
    }

    @Test
    void should_construct() {
	CandidateCreateDto actual = new CandidateCreateDto();
	assertNotNull(actual);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/candidateCreation.csv", delimiter = ';')
    void should_return_toString(String json) {
	CandidateCreateDto memberCreateDto = jsonConvert(json,
		CandidateCreateDto.class);
	String expected = "{member={id=1}, task={id=1}, priority=1, status=AVAILABLE}";
	String actual = memberCreateDto.toString();
	System.out.println(actual);
	assertEquals(expected, actual);
    }
}
