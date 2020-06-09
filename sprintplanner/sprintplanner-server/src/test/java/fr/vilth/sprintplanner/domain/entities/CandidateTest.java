package fr.vilth.sprintplanner.domain.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import fr.vilth.sprintplanner.SetupUnitTest;
import fr.vilth.sprintplanner.domain.types.Status;

public class CandidateTest extends SetupUnitTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/candidateCreation.csv", delimiter = ';')
    void should_increment_priority(String json) {
	Candidate candidate = jsonConvert(json, Candidate.class);
	int actual = candidate.getPriority();
	candidate.incrementPriority();
	int expected = candidate.getPriority();
	assertEquals(expected, actual + 1);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/candidateCreation.csv", delimiter = ';')
    void should_become_current(String json) {
	Candidate candidate = jsonConvert(json, Candidate.class);
	candidate.becomesCurrent();
	assertEquals(Status.CURRENT, candidate.getStatus());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/candidateCreation.csv", delimiter = ';')
    void should_become_previous(String json) {
	Candidate candidate = jsonConvert(json, Candidate.class);
	candidate.becomesPrevious();
	assertEquals(Status.AVAILABLE, candidate.getStatus());
	assertEquals(0, candidate.getPriority());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/candidateCreation.csv", delimiter = ';')
    void should_return_true_if_available(String json) {
	Candidate candidate = jsonConvert(json, Candidate.class);
	candidate.becomesPrevious();
	assertTrue(candidate.isAvailable());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/candidateCreation.csv", delimiter = ';')
    void should_return_unavailable_if_not_available(String json) {
	Candidate candidate = jsonConvert(json, Candidate.class);
	candidate.becomesCurrent();
	assertFalse(candidate.isAvailable());
    }
}
