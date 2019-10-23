package fr.vilth.sprintplanner.domain.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Objects;

import org.junit.jupiter.api.Test;

import fr.vilth.sprintplanner.SetupUnitTest;

public class MemberTest extends SetupUnitTest {

	protected MemberTest() throws IOException {
		super();
	}

	private static final String ACTUAL_MEMBER_JSON = "{\"id\":1, \"firstname\":\"actual\", \"lastname\":\"member\", \"email\":\"actual@member\"}";
	private static final String DIFFERENT_MEMBER_JSON = "{\"id\":3, \"firstname\":\"different\", \"lastname\":\"member\", \"email\":\"different@member\"}";

	private Member actualMember = MAPPER.readValue(ACTUAL_MEMBER_JSON, Member.class);
	private Member sameMember = MAPPER.readValue(ACTUAL_MEMBER_JSON, Member.class);
	private Member differentMember = MAPPER.readValue(DIFFERENT_MEMBER_JSON, Member.class);

	@Test
	void should_construct() {
		Member actual = new Member();
		assertNotNull(actual);
	}

	@Test
	void should_return_hashcode() {
		int actual = actualMember.hashCode();
		int expected = Objects.hash("actual@member");
		assertEquals(expected, actual);
	}

	@Test
	void should_be_equal() {
		boolean condition = actualMember.equals(sameMember);
		assertTrue(condition);
	}

	@Test
	void should_not_be_equal() {
		boolean condition = actualMember.equals(differentMember);
		assertFalse(condition);
	}

	@Test
	void should_be_equal_to_itself() {
		boolean condition = actualMember.equals(actualMember);
		assertTrue(condition);
	}

	@Test
	void should_not_be_equal_to_other_type_object() {
		Object otherType = new Object();
		boolean condition = actualMember.equals(otherType);
		assertFalse(condition);
	}

	@Test
	void should_return_to_string() {
		String expected = "{id=1, firstname=actual, lastname=member, email=actual@member}";
		String actual = actualMember.toString();
		assertEquals(expected, actual);
	}
}
