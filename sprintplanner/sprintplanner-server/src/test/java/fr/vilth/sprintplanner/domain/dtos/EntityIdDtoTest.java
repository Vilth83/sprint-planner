package fr.vilth.sprintplanner.domain.dtos;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import fr.vilth.sprintplanner.SetupUnitTest;

public class EntityIdDtoTest extends SetupUnitTest {

	private final static String ENTITY_DTO_JSON = "{\"id\":1}";
	private final EntityIdDto actualEntity = MAPPER.readValue(ENTITY_DTO_JSON, EntityIdDto.class);;

	protected EntityIdDtoTest() throws IOException {
		// Empty constructor
	}

	@Test
	void should_construct() {
		EntityIdDto actual = new EntityIdDto();
		assertNotNull(actual);
	}

	@Test
	void should_return_toString() {
		String expected = "{id=1}";
		String actual = actualEntity.toString();
		assertEquals(expected, actual);

	}
}
