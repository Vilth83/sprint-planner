package fr.vilth.sprintplanner.domain.dtos.custom_user;

/**
 * A projection of a {@code CustomUser} for user info.
 */
public interface CustomUserInfoDto {

    Long getId();

    String getUsername();

    String getFirstname();

    String getLastname();
}
