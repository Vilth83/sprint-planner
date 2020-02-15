package fr.vilth.sprintplanner.api.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import fr.vilth.sprintplanner.SetupIntTest;
import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.project.ProjectCreateDto;
import fr.vilth.sprintplanner.domain.dtos.project.ProjectViewDto;

@Transactional
public class ProjectControllerTest extends SetupIntTest {

    @Autowired
    private ProjectController controller;

    @Test
    void should_return_project() {
	ProjectViewDto project = controller.getProject();
	assertEquals(2, project.getSprintDuration());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/projectCreation.csv", delimiter = ';')
    void should_save_project(String json) {
	ProjectCreateDto inputs = jsonConvert(json, ProjectCreateDto.class);
	EntityIdDto expected = controller.save(inputs);
	assertNotNull(expected);
    }
}
