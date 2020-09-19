package fr.vilth.sprintplanner.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.vilth.sprintplanner.api.services.CustomUserDetailsService;
import fr.vilth.sprintplanner.domain.dtos.custom_user.CustomUserCreateDto;
import fr.vilth.sprintplanner.domain.dtos.custom_user.CustomUserManagementDto;
import fr.vilth.sprintplanner.security.annotations.HasRoleAdmin;

/**
 * A {@code RestController} to handle {@code CustomUserDetails}.
 * 
 * @author Thierry VILLEPREUX
 */
@RestController
@RequestMapping("/users")
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

    @GetMapping
    protected boolean existsByUsername(@RequestParam String username) {
	return service.usernameIsUnique(username);
    }

    @HasRoleAdmin
    @GetMapping("/all")
    protected List<CustomUserManagementDto> findAll() {
	return service.findAll();
    }

    @HasRoleAdmin
    @PutMapping("/{id}")
    protected void toggleAccountActivation(@PathVariable Long id,
	    @RequestBody CustomUserManagementDto inputs) {
	service.toggleAccountActivation(id, inputs);
    }

    @HasRoleAdmin
    @PutMapping("/role/{id}")
    protected void toggleAdminRole(@PathVariable Long id,
	    @RequestBody CustomUserManagementDto inputs) {
	service.toggleAdminRole(id, inputs);
    }
}
