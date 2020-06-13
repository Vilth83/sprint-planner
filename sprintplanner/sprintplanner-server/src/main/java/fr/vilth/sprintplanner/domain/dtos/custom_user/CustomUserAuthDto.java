package fr.vilth.sprintplanner.domain.dtos.custom_user;

import java.util.Set;

import fr.vilth.sprintplanner.domain.entities.Role;

/**
 * {@code Dto} representation of a {@code CustomUser} for authentication.
 */
@SuppressWarnings("unused") // Required for Jackson mapping
public class CustomUserAuthDto {

    private Long id;

    private String username;

    private String password;

    private Set<Role> roles;

    private boolean enabled;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    public Long getId() {
	return id;
    }

    public String getUsername() {
	return username;
    }

    public String getPassword() {
	return password;
    }

    public Set<Role> getRoles() {
	return roles;
    }

    public boolean isEnabled() {
	return enabled;
    }

    public boolean isAccountNonExpired() {
	return accountNonExpired;
    }

    public boolean isAccountNonLocked() {
	return accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
	return credentialsNonExpired;
    }
}
