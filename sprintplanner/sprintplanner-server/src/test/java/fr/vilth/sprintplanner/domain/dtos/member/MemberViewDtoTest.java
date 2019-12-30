package fr.vilth.sprintplanner.domain.dtos.member;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import fr.vilth.sprintplanner.SetupUnitTest;

/**
 * Test upon MemberViewDto.
 * 
 * @author Thierry VILLEPREUX
 */
public class MemberViewDtoTest extends SetupUnitTest {

    private static final String MEMBER_CREATE_JSON = "{\"id\":1, \"firstname\":\"actual\", \"lastname\":\"member\", \"email\":\"actual@member\", \"shift\":\"PAR\"}";

    private final MemberViewDto memberCreateDto = MAPPER
	    .readValue(MEMBER_CREATE_JSON, MemberViewDto.class);

    MemberViewDtoTest() throws IOException {
	// Empty Constructor
    }

    @Test
    void should_construct() {
	MemberViewDto actual = new MemberViewDto();
	assertNotNull(actual);
    }

    @Test
    void should_return_toString() {
	String expected = "{id=1, firstname=actual, lastname=member, email=actual@member, shift=PAR}";
	String actual = memberCreateDto.toString();
	assertEquals(expected, actual);
    }
}