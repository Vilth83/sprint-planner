package fr.vilth.sprintplanner.api.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.vilth.sprintplanner.api.services.CustomUserDetailsService;
import fr.vilth.sprintplanner.domain.dtos.custom_user.CustomUserCreateDto;

@RestController
public class CustomUserDetailsController {

    private final CustomUserDetailsService service;

    protected CustomUserDetailsController(CustomUserDetailsService service) {
	this.service = service;
    }

    /**
     * Creates a new user with given username and password, and default role.
     *
     * @param inputs the inputs
     */
    @PostMapping
    protected void create(@RequestBody @Valid CustomUserCreateDto inputs) {
	service.create(inputs);
    }
}
