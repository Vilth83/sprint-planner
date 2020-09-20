package fr.vilth.sprintplanner.api.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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

@TestMethodOrder(OrderAnnotation.class)
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
    @Order(1)
    void should_create_a_new_user(String json) {
	CustomUserCreateDto user = jsonConvert(json, CustomUserCreateDto.class);
	controller.create(user);
	UserDetails actual = service.loadUserByUsername(TEST_USER);
	assertEquals(actual.getUsername(), TEST_USER);
    }

    @Test
    @Order(2)
    void should_return_existing_user() {
	UserDetails actual = service.loadUserByUsername(ADMIN);
	assertEquals(actual.getUsername(), ADMIN);
    }

    @Test
    @Order(3)
    void should_fail_returning_existing_user() {
	assertThrows(
		UsernameNotFoundException.class,
		() -> service.loadUserByUsername(NOT_EXISTING));
    }

    @Test
    @Order(4)
    void should_return_true_with_unique_username() {
	boolean actual = controller.existsByUsername(UNIQUE);
	assertTrue(actual);
    }

    @Test
    @Order(5)
    void should_return_false_with_existing_username() {
	boolean actual = controller.existsByUsername(ADMIN);
	assertFalse(actual);
    }

    @Test
    @Order(6)
    void should_return_user_info() {
	CustomUserInfoDto info = service.getCurrentUserInfo(1L);
	assertNotNull(info);
    }

    @Test
    @Order(7)
    @WithMockUser(username = "admin", password = "pwd", roles = "ADMIN")
    void should_return_all_users() {
	List<CustomUserManagementDto> users = controller.findAll();
	assertEquals(users.size(), 3);
    }

    @Test
    @Order(8)
    @WithMockUser(username = "admin", password = "pwd", roles = "ADMIN")
    void should_activate_account() {
	List<CustomUserManagementDto> users = controller.findAll();
	CustomUserManagementDto tested = users.stream()
		.filter(usr -> !usr.isActivated()).findFirst().get();
	controller.toggleAccountActivation(3L, tested);
	List<CustomUserManagementDto> actual = controller.findAll();
	assertTrue(
		actual.stream().allMatch(CustomUserManagementDto::isActivated));
    }

    @Test
    @Order(9)
    @WithMockUser(username = "admin", password = "pwd", roles = "ADMIN")
    void should_deactivate_account() {
	List<CustomUserManagementDto> users = controller.findAll();
	controller.toggleAccountActivation(3L, users.get(2));
	List<CustomUserManagementDto> actual = controller.findAll();
	assertFalse(
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
