package fr.vilth.sprintplanner.api.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;

import fr.vilth.sprintplanner.SetupIntTest;
import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.project.ProjectCreateDto;
import fr.vilth.sprintplanner.domain.dtos.project.ProjectViewDto;

/**
 * Integration test of ProjectController
 * 
 * @author Thierry VILLEPREUX
 *
 */
@Transactional
public class ProjectControllerTest extends SetupIntTest {

	@Autowired
	private ProjectController controller;

	@Test
	@WithMockUser(username = "admin", password = "pwd", roles = "ADMIN")
	void should_return_project() {
		ProjectViewDto project = controller.getProject();
		assertEquals(2, project.getSprintDuration());
	}

	@Test
	void should_fail_returning_project_for_unauthenticated_user() {
		Assertions.assertThrows(AuthenticationCredentialsNotFoundException.class, () -> controller.getProject());
	}

	@Test
	@WithMockUser(username = "user", password = "pwd", roles = "USER")
	void should_fail_returning_project_for_forbidden_user() {
		Assertions.assertThrows(AccessDeniedException.class, () -> controller.getProject());
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/projectCreation.csv", delimiter = ';')
	@WithMockUser(username = "admin", password = "pwd", roles = "ADMIN")
	void should_save_project(String json) {
		ProjectCreateDto inputs = jsonConvert(json, ProjectCreateDto.class);
		EntityIdDto expected = controller.save(inputs);
		assertNotNull(expected);
	}

	@Test
	void should_fail_saving_project_for_unauthenticated_user() {
		ProjectCreateDto inputs = null;
		Assertions.assertThrows(AuthenticationCredentialsNotFoundException.class, () -> controller.save(inputs));
	}

	@Test
	@WithMockUser(username = "user", password = "pwd", roles = "USER")
	@CsvFileSource(resources = "/projectCreation.csv", delimiter = ';')
	void should_fail_saving_project_for_forbidden_user() {
		ProjectCreateDto inputs = null;
		Assertions.assertThrows(AccessDeniedException.class, () -> controller.save(inputs));
	}
}
