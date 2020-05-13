package fr.vilth.sprintplanner.domain.dtos.member;

import fr.vilth.sprintplanner.domain.types.Shift;

/**
 * A DTO representation of a {@code Member}.
 * <p>
 * this DTO give all informations about a {@code Member}.
 * 
 * @author Thierry VILLEPREUX
 */
public class MemberViewDto {

    private Long id;

    private String firstname;

    private String lastname;

    private String email;

    private Shift shift;

    protected MemberViewDto() {
	// private empty no-arg constructor
    }

    public String getEmail() {
	return email;
    }

    public String buildFullName() {
	return firstname + " " + lastname;
    }

    @Override
    public String toString() {
	return "{id=" + id + ", firstname=" + firstname + ", lastname="
		+ lastname + ", email=" + email + ", shift=" + shift + "}";
    }
}
