package fr.vilth.sprintplanner.api.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import fr.vilth.sprintplanner.SetupIntTest;
import fr.vilth.sprintplanner.api.services.implementation.CustomUserDetailsServiceImpl;
import fr.vilth.sprintplanner.configuration.exceptions.ResourceNotFoundException;
import fr.vilth.sprintplanner.domain.dtos.custom_user.CustomUserCreateDto;
import fr.vilth.sprintplanner.domain.dtos.custom_user.CustomUserInfoDto;

public class CustomUserDetailsControllerTest extends SetupIntTest {

    @Autowired
    CustomUserDetailsController controller;

    @Autowired
    CustomUserDetailsServiceImpl service;

    @ParameterizedTest
    @CsvFileSource(resources = "/userCreation.csv", delimiter = ';')
    void should_create_a_new_user(String json) {
	CustomUserCreateDto user = jsonConvert(json, CustomUserCreateDto.class);
	controller.create(user);
	UserDetails actual = service.loadUserByUsername("testUser");
	Assertions.assertEquals(actual.getUsername(), "testUser");
    }

    @Test
    void should_return_existing_user() {
	UserDetails actual = service.loadUserByUsername("admin");
	Assertions.assertEquals(actual.getUsername(), "admin");
    }

    @Test
    void should_fail_returning_existing_user() {
	Assertions.assertThrows(
		UsernameNotFoundException.class,
		() -> service.loadUserByUsername("not_existing"));
    }

    @Test
    void should_return_true_with_unique_username() {
	boolean actual = service.usernameIsUnique("unique");
	Assertions.assertTrue(actual);
    }

    @Test
    void should_return_false_with_existing_username() {
	boolean actual = service.usernameIsUnique("admin");
	Assertions.assertFalse(actual);
    }

    @Test
    void should_return_user_info() {
	CustomUserInfoDto info = service.getCurrentUserInfo(1L);
	Assertions.assertNotNull(info);
    }

    @Test
    void should_fail_returning_info_with_wrong_id() {
	Throwable exception = Assertions.assertThrows(
		ResourceNotFoundException.class,
		() -> service.getCurrentUserInfo(999L));
	assertEquals(exception.getMessage(), "with id:999");
    }
}
