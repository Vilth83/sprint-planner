package fr.vilth.sprintplanner.domain.utils;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.common.collect.Sets;

import fr.vilth.sprintplanner.SetupUnitTest;
import fr.vilth.sprintplanner.domain.entities.Candidate;
import fr.vilth.sprintplanner.domain.types.Status;
import fr.vilth.sprintplanner.domain.utils.RoundRobinHandler;

public class RoundRobinHandlerTest extends SetupUnitTest {

    private Candidate unavailable;

    private Candidate available;

    private Candidate current;

    private Set<Candidate> candidates;

    @BeforeEach
    void setListUp() {
	unavailable = jsonConvert(
		"{\"member\":{\"id\":-2}, \"task\":{\"id\":-4},\"status\":\"UNAVAILABLE\", \"priority\":1}",
		Candidate.class);
	available = jsonConvert(
		"{\"member\":{\"id\":-3}, \"task\":{\"id\":-4},\"status\":\"AVAILABLE\", \"priority\":2}",
		Candidate.class);
	current = jsonConvert(
		"{\"member\":{\"id\":-4}, \"task\":{\"id\":-4},\"status\":\"CURRENT\", \"priority\":3}",
		Candidate.class);
	candidates = Sets.newHashSet(unavailable,
		available,
		current);
    }

    @Test
    void should_change_priorities() {
	RoundRobinHandler.rotate(candidates);
	Assertions.assertAll(
		() -> Assertions.assertEquals(unavailable.getPriority(), 2),
		() -> Assertions.assertEquals(current.getPriority(), 0),
		() -> Assertions.assertEquals(available.getPriority(), 3));
    }

    @Test
    void should_change_statuses() {
	RoundRobinHandler.rotate(candidates);
	Assertions.assertAll(
		() -> Assertions.assertEquals(unavailable.getStatus(),
			Status.AVAILABLE),
		() -> Assertions.assertEquals(current.getStatus(),
			Status.AVAILABLE),
		() -> Assertions.assertEquals(available.getStatus(),
			Status.CURRENT));
    }
}
