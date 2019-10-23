package fr.vilth.sprintplanner.domain.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import fr.vilth.sprintplanner.SetupUnitTest;

public class MemberCreateDtoTest extends SetupUnitTest {
	
	private final String MemberCreateDtoJson = "{\"firstname\":\"actual\", \"lastname\":\"member\", \"email\":\"actual@member\"}";
	
	private MemberCreateDto memberCreateDto = MAPPER.readValue(MemberCreateDtoJson, MemberCreateDto.class);
	
	MemberCreateDtoTest() throws IOException {
		// empty constructor to handle ObjectMapper exceptions
	}


	@Test
	void should_construct() {
		MemberCreateDto actual = new MemberCreateDto();
		assertNotNull(actual);
	}
	
	@Test
	void should_return_toString() {
		String expected = "{firstname=actual, lastname=member, email=actual@member}";
		String actual = memberCreateDto.toString();
			assertEquals(expected, actual);
	}
}
