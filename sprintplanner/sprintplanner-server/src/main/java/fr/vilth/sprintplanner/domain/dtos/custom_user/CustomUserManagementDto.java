package fr.vilth.sprintplanner.domain.dtos.custom_user;

import fr.vilth.sprintplanner.domain.entities.Role;

public class CustomUserManagementDto {

    private long id;

    private String firstname;

    private String lastname;

    private String username;

    private Role role;

    private boolean activated;

    public void setActivated(boolean activated) {
	this.activated = activated;
    }

    public void setRole(Role role) {
	this.role = role;
    }

    public boolean isActivated() {
	return activated;
    }
}
