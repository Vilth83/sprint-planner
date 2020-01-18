package fr.vilth.sprintplanner.domain.dtos.member;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import fr.vilth.sprintplanner.SetupUnitTest;

/**
 * Tests upon {@code MemberCreateDto}.
 * 
 * @author Thierry VILLEPREUX
 */
public class MemberDeleteDtoTest extends SetupUnitTest {

    @Test
    void should_construct() {
	MemberDeleteDto actual = new MemberDeleteDto();
	assertNotNull(actual);
    }
}
