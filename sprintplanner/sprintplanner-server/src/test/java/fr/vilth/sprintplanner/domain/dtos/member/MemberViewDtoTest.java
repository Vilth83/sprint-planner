package fr.vilth.sprintplanner.domain.dtos.member;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import fr.vilth.sprintplanner.SetupUnitTest;

/**
 * Test upon MemberViewDto.
 * 
 * @author Thierry VILLEPREUX
 */
public class MemberViewDtoTest extends SetupUnitTest {

    MemberViewDtoTest() throws IOException {
	// Empty Constructor
    }

    @Test
    void should_construct() {
	MemberViewDto actual = new MemberViewDto();
	assertNotNull(actual);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/memberViewDto.csv", delimiter = ';')
    void should_return_toString(String json) {
	MemberViewDto tested = jsonConvert(json, MemberViewDto.class);
	String expected = "{id=1, firstname=actual, lastname=member, email=actual@member, shift=PAR}";
	String actual = tested.toString();
	assertEquals(expected, actual);
    }
}
