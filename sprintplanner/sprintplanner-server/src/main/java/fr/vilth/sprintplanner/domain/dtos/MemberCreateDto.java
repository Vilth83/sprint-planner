package fr.vilth.sprintplanner.domain.dtos;

import javax.validation.constraints.Email;

/**
 * DTO representing {@code Member} data to be persisted in database.
 * 
 * @author Thierry VILLEPREUX
 *
 */
public class MemberCreateDto {

    /**
     * Protected empty no-arg constructor
     */
    protected MemberCreateDto() {
	//
    }
    
    // TODO : unique constraint validator
    private String firstname;

    private String lastname;

    @Email
    private String email;

    @Override
    public String toString() {
	return "{firstname=" + firstname + ", lastname=" + lastname + ", email="
		+ email + "}";
    }
    
    
    
    
}
