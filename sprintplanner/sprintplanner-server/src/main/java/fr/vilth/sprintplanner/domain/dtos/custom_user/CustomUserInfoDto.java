package fr.vilth.sprintplanner.domain.dtos.custom_user;

import java.util.Set;

import fr.vilth.sprintplanner.domain.entities.Role;

/**
 * {@code Dto} representation of a {@code CustomUser}
 */
@SuppressWarnings("unused") // Required for Jackson mapping
public class CustomUserInfoDto {

    private Long id;

    private String username;

    private String firstname;

    private String lastname;

    private Set<Role> roles;
}
