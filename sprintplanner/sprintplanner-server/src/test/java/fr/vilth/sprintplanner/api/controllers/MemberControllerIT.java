package fr.vilth.sprintplanner.api.controllers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import fr.vilth.sprintplanner.api.IntegrationTestSetup;
import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.MemberCreateDto;

@Transactional
public class MemberControllerIT extends IntegrationTestSetup {

	@Autowired
	private MemberController controller;
	private EntityIdDto entityId;

	@BeforeEach
	void addEntity() {
		String existing = "{\"firstname\":\"existing\", \"lastname\":\"test\", \"email\":\"existing@test\"}";
		MemberCreateDto existingEntity = jsonConvert(existing, MemberCreateDto.class);
		entityId = controller.save(existingEntity);
	}

	@AfterEach
	void deleteEntity() {
		// TODO : implement deletion
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/memberCreation.csv", delimiter = ';')
	void should_save_new_member(String json) {
		MemberCreateDto dto = jsonConvert(json, MemberCreateDto.class);
		EntityIdDto actual = controller.save(dto);
		assertNotNull(actual);
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/failValidation.csv", delimiter = ';')
	void should_fail_validation(String json) {
		MemberCreateDto tested = jsonConvert(json, MemberCreateDto.class);
		assertFalse(isValid(tested));
	}
}
