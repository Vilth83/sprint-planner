package fr.vilth.sprintplanner.domain.dtos.member;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import fr.vilth.sprintplanner.SetupUnitTest;

/**
 * Tests upon {@code MemberCreateDto}.
 * 
 * @author vilth
 */
public class MemberCreateDtoTest extends SetupUnitTest {

    @Test
    void should_construct() {
	MemberCreateDto actual = new MemberCreateDto();
	assertNotNull(actual);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/memberCreation.csv", delimiter = ';')
    void should_return_toString(String json) {
	String expected = "{firstname=test, lastname=test, email=test@test, shift=PAR}";
	MemberCreateDto tested = jsonConvert(json, MemberCreateDto.class);
	String actual = tested.toString();
	assertEquals(expected, actual);
    }
}
