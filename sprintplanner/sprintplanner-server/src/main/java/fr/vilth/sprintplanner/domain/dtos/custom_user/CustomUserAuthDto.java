package fr.vilth.sprintplanner.domain.dtos.custom_user;

import java.util.Set;

import fr.vilth.sprintplanner.domain.entities.CustomUser;
import fr.vilth.sprintplanner.domain.entities.Role;

/**
 * A projection of a {@code CustomUser} for authentication.
 * 
 * @see CustomUser
 */
@SuppressWarnings("javadoc") // Getters of CustomUser
public interface CustomUserAuthDto {

	Long getId();

	String getUsername();

	String getPassword();

	Set<Role> getRoles();

	boolean isEnabled();

	boolean isAccountNonExpired();

	boolean isAccountNonLocked();

	boolean isCredentialsNonExpired();
}
