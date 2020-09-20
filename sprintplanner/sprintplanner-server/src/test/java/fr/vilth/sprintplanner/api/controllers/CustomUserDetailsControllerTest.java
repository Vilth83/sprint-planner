package fr.vilth.sprintplanner.api.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;

import fr.vilth.sprintplanner.SetupIntTest;
import fr.vilth.sprintplanner.api.services.implementation.CustomUserDetailsServiceImpl;
import fr.vilth.sprintplanner.configuration.exceptions.ResourceNotFoundException;
import fr.vilth.sprintplanner.domain.dtos.custom_user.CustomUserCreateDto;
import fr.vilth.sprintplanner.domain.dtos.custom_user.CustomUserInfoDto;
import fr.vilth.sprintplanner.domain.dtos.custom_user.CustomUserManagementDto;

public class CustomUserDetailsControllerTest extends SetupIntTest {

    private static final String UNIQUE = "unique";

    private static final String NOT_EXISTING = "not_existing";

    private static final String TEST_USER = "testUser";

    private static final String ADMIN = "admin";

    @Autowired
    CustomUserDetailsController controller;

    @Autowired
    CustomUserDetailsServiceImpl service;

    @ParameterizedTest
    @CsvFileSource(resources = "/userCreation.csv", delimiter = ';')
    void should_create_a_new_user(String json) {
	CustomUserCreateDto user = jsonConvert(json, CustomUserCreateDto.class);
	controller.create(user);
	UserDetails actual = service.loadUserByUsername(TEST_USER);
	assertEquals(actual.getUsername(), TEST_USER);
    }

    @Test
    void should_return_existing_user() {
	UserDetails actual = service.loadUserByUsername(ADMIN);
	assertEquals(actual.getUsername(), ADMIN);
    }

    @Test
    void should_fail_returning_existing_user() {
	assertThrows(
		UsernameNotFoundException.class,
		() -> service.loadUserByUsername(NOT_EXISTING));
    }

    @Test
    void should_return_true_with_unique_username() {
	boolean actual = controller.existsByUsername(UNIQUE);
	assertTrue(actual);
    }

    @Test
    void should_return_false_with_existing_username() {
	boolean actual = controller.existsByUsername(ADMIN);
	assertFalse(actual);
    }

    @Test
    void should_return_user_info() {
	CustomUserInfoDto info = service.getCurrentUserInfo(1L);
	assertNotNull(info);
    }

    @Test
    @WithMockUser(username = "admin", password = "pwd", roles = "ADMIN")
    void should_return_all_users() {
	List<CustomUserManagementDto> users = controller.findAll();
	assertEquals(users.size(), 2);
    }

    @Test
    @WithMockUser(username = "admin", password = "pwd", roles = "ADMIN")
    void should_deactivate_activate_account() {
	List<CustomUserManagementDto> users = controller.findAll();
	CustomUserManagementDto tested = users.stream()
		.findFirst().get();
	Long id = tested.getId();
	controller.toggleAccountActivation(id, tested);
	List<CustomUserManagementDto> actual = controller.findAll();
	assertFalse(
		actual.stream().allMatch(CustomUserManagementDto::isActivated));
	users = controller.findAll();
	tested = users.stream().filter(usr -> !usr.isActivated())
		.findFirst().get();
	controller.toggleAccountActivation(tested.getId(), tested);
	actual = controller.findAll();
	assertTrue(
		actual.stream().allMatch(CustomUserManagementDto::isActivated));
    }

    @Test
    void should_fail_returning_info_with_wrong_id() {
	Throwable exception = assertThrows(
		ResourceNotFoundException.class,
		() -> service.getCurrentUserInfo(999L));
	assertEquals(exception.getMessage(), "with id:999");
    }
}
