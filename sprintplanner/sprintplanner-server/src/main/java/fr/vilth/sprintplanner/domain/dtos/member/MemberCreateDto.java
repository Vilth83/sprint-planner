package fr.vilth.sprintplanner.domain.dtos.member;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import fr.vilth.sprintplanner.domain.types.Shift;
import fr.vilth.sprintplanner.domain.validators.UniqueEmail;

/**
 * DTO representing {@code Member} data to be persisted in database.
 * 
 * @author Thierry VILLEPREUX
 */
public class MemberCreateDto {

    /**
     * Protected empty no-arg constructor
     */
    protected MemberCreateDto() {
	//
    }

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @UniqueEmail
    @Email
    @NotBlank
    private String email;

    private Shift shift;

    @Override
    public String toString() {
	return "{firstname=" + firstname + ", lastname=" + lastname + ", email="
		+ email + ", shift=" + shift + "}";
    }
}
