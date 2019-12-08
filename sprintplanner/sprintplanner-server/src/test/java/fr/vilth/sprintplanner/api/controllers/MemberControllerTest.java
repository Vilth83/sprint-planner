package fr.vilth.sprintplanner.api.controllers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import fr.vilth.sprintplanner.api.SetupIntTest;
import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.member.MemberCreateDto;
import fr.vilth.sprintplanner.domain.dtos.member.MemberUpdateDto;
import fr.vilth.sprintplanner.domain.dtos.member.MemberViewDto;

/**
 * Tests upon {@code MemberController}.
 * 
 * @author vilth
 */
@Transactional
public class MemberControllerTest extends SetupIntTest {

    @Autowired
    private MemberController controller;

    @BeforeEach
    void addEntity() {
	String existing = "{\"firstname\":\"existing\", \"lastname\":\"test\", \"email\":\"existing@test\"}";
	MemberCreateDto existingEntity = jsonConvert(existing,
		MemberCreateDto.class);
	controller.save(existingEntity);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/memberCreation.csv", delimiter = ';')
    void should_save_new_member(String json) {
	MemberCreateDto dto = jsonConvert(json, MemberCreateDto.class);
	EntityIdDto actual = controller.save(dto);
	assertNotNull(actual);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/memberCreation.csv", delimiter = ';')
    void should_return_all_members(String json) {
	MemberCreateDto saved = jsonConvert(json, MemberCreateDto.class);
	controller.save(saved);
	Set<MemberViewDto> tested = controller.findAll();
	assertEquals(2, tested.size());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/failValidation.csv", delimiter = ';')
    void should_fail_validation(String json) {
	MemberCreateDto tested = jsonConvert(json, MemberCreateDto.class);
	assertFalse(isValid(tested));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/failModificationValidation.csv", delimiter = ';')
    void should_fail_validation_when_modifying(String json) {
	MemberUpdateDto tested = jsonConvert(json, MemberUpdateDto.class);
	assertFalse(isValid(tested));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/memberModification.csv", delimiter = ';')
    void should_modify_existing_member(String json) {
	MemberUpdateDto tested = jsonConvert(json, MemberUpdateDto.class);
	assertDoesNotThrow(() -> controller.update(tested));
    }

    @Test
    @Sql("classpath:/sqlTestCases/deletion.sql")
    void should_delete_by_id() {
	assertDoesNotThrow(() -> controller.delete(1L));
    }
}
