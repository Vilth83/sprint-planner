package fr.vilth.sprintplanner.domain.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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
    private String email;

    @Override
    public String toString() {
	return "{firstname=" + firstname + ", lastname=" + lastname + ", email="
		+ email + "}";
    }
}
