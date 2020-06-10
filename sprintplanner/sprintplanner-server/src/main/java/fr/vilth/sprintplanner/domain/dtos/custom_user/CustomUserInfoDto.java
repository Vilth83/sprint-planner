package fr.vilth.sprintplanner.domain.dtos.custom_user;

import fr.vilth.sprintplanner.domain.entities.CustomUser;

/**
 * A projection of a {@code CustomUser} for user info.
 * 
 * @see CustomUser
 */
@SuppressWarnings("javadoc")
public interface CustomUserInfoDto {

	Long getId();

	String getUsername();

	String getFirstname();

	String getLastname();
}
