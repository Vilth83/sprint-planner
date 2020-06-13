package fr.vilth.sprintplanner.api.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

import fr.vilth.sprintplanner.SetupIntTest;
import fr.vilth.sprintplanner.commons.exceptions.ResourceNotFoundException;
import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateCreateDto;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateDeleteDto;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateUpdateDto;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateViewDto;
import fr.vilth.sprintplanner.domain.types.Shift;

/**
 * Integration tests upon {@code CandidateController}.
 * 
 * @author Thierry VILLEPREUX
 */
public class CandidateControllerTest extends SetupIntTest {

    private static final String RELEASER = "releaser";

    @Autowired
    private CandidateController controller;

    @ParameterizedTest
    @CsvFileSource(resources = "/candidateCreation.csv", delimiter = ';')
    @WithMockUser(username = "admin", password = "pwd", roles = "ADMIN")
    void should_save_new_candidate(String json) {
	CandidateCreateDto dto = jsonConvert(json, CandidateCreateDto.class);
	EntityIdDto actual = controller.save(dto);
	assertNotNull(actual);
	controller.delete(modelMapper.map(actual, CandidateDeleteDto.class));
    }

    @Test
    void should_return_candidates() {
	Set<CandidateViewDto> actual = controller.findAllByTaskName(RELEASER);
	assertEquals(3, actual.size());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/candidateCreation.csv", delimiter = ';')
    @WithMockUser(username = "admin", password = "pwd", roles = "ADMIN")
    void should_delete_candidate(String json) {
	Set<CandidateViewDto> actual = controller.findAllByTaskName(RELEASER);
	CandidateCreateDto dto = jsonConvert(json, CandidateCreateDto.class);
	EntityIdDto tested = controller.save(dto);
	controller.delete(modelMapper.map(tested, CandidateDeleteDto.class));
	Set<CandidateViewDto> expected = controller.findAllByTaskName(RELEASER);
	assertEquals(expected.size(), actual.size());
    }

    @Test
    @WithMockUser(username = "user", password = "pwd", roles = "USER")
    void should_update_candidate() {
	CandidateUpdateDto update = jsonConvert(
		"{\"id\":-2, \"status\":\"UNAVAILABLE\"}",
		CandidateUpdateDto.class);
	controller.update(update, -2L);
	Set<CandidateViewDto> candidates = controller
		.findAllByTaskName(RELEASER);
	assertTrue(candidates.stream().anyMatch(
		candidate -> candidate.toString().contains("id=-2") && candidate
			.toString().contains("status=UNAVAILABLE")));
    }

    @Test
    void should_return_current_candidate() {
	CandidateViewDto expected = controller.getCurrentByTask("tester", null);
	assertTrue(expected.toString().contains("status=CURRENT"));
    }

    @Test
    void should_return_current_candidate_not_found() {
	Assertions.assertThrows(ResourceNotFoundException.class,
		() -> controller.getCurrentByTask("notask", null));
    }

    @Test
    void should_return_current_candidate_by_shift() {
	CandidateViewDto actual = controller.getCurrentByTask("support",
		Shift.BGL);
	Assertions.assertAll(() -> assertNotNull(actual),
		() -> assertTrue(actual.toString().contains("shift=BGL")));
    }

    @Test
    void should_find_all_members_by_task_and_shift() {
	Set<CandidateViewDto> actuals = controller
		.findAllByTaskNameAndMemberShift("support", Shift.PAR);
	assertEquals(2, actuals.size());
    }
}
