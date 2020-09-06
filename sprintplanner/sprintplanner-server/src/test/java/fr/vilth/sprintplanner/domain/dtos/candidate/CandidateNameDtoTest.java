package fr.vilth.sprintplanner.domain.dtos.candidate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.vilth.sprintplanner.SetupUnitTest;

public class CandidateNameDtoTest extends SetupUnitTest {

    @Test
    void should_set_firstname() {
	CandidateNameDto tested = new CandidateNameDto();
	tested.setFirstname("firstname");
	Assertions.assertEquals("firstname", tested.getFirstname());
    }

    @Test
    void should_set_lastname() {
	CandidateNameDto tested = new CandidateNameDto();
	tested.setLastname("lastname");
	Assertions.assertEquals("lastname", tested.getLastname());
    }
}
