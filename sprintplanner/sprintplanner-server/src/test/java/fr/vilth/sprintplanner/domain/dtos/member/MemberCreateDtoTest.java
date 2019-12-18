package fr.vilth.sprintplanner.domain.dtos.member;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import fr.vilth.sprintplanner.SetupUnitTest;

/**
 * Tests upon {@code MemberCreateDto}.
 * 
 * @author vilth
 */
public class MemberCreateDtoTest extends SetupUnitTest {

    private static final String MEMBER_CREATE_JSON = "{\"firstname\":\"actual\", \"lastname\":\"member\", \"email\":\"actual@member\", \"shift\":\"PAR\"}";

    private final MemberCreateDto memberCreateDto = MAPPER
	    .readValue(MEMBER_CREATE_JSON, MemberCreateDto.class);

    MemberCreateDtoTest() throws IOException {
	// Empty Constructor
    }

    @Test
    void should_construct() {
	MemberCreateDto actual = new MemberCreateDto();
	assertNotNull(actual);
    }

    @Test
    void should_return_toString() {
	String expected = "{firstname=actual, lastname=member, email=actual@member, shift=PAR}";
	String actual = memberCreateDto.toString();
	assertEquals(expected, actual);
    }
}
