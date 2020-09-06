package fr.vilth.sprintplanner.domain.dtos.member;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Assertions;
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

    @ParameterizedTest
    @CsvFileSource(resources = "/memberViewDto.csv", delimiter = ';')
    void should_return_properties(String json) {
	MemberViewDto actual = jsonConvert(json, MemberViewDto.class);
	Assertions.assertAll(
		() -> assertEquals("actual", actual.getFirstname()),
		() -> assertEquals("member", actual.getLastname()),
		() -> assertEquals("actual@member", actual.getEmail()));
    }
}
