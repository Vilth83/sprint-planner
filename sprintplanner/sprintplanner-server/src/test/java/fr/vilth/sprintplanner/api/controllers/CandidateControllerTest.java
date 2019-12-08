package fr.vilth.sprintplanner.api.controllers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.transaction.Transactional;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import fr.vilth.sprintplanner.SetupIntTest;
import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateCreateDto;

/**
 * Integration tests upon {@code CandidateController}.
 * 
 * @author Thierry VILLEPREUX
 */
@Transactional
public class CandidateControllerTest extends SetupIntTest {

    @Autowired
    private CandidateController controller;

    @ParameterizedTest
    @CsvFileSource(resources = "/candidateCreation.csv", delimiter = ';')
    void should_save_new_member(String json) {
	CandidateCreateDto dto = jsonConvert(json, CandidateCreateDto.class);
	EntityIdDto actual = controller.save(dto);
	assertNotNull(actual);
    }
}
