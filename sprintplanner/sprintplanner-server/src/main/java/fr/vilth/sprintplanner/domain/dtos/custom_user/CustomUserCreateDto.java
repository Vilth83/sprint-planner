package fr.vilth.sprintplanner.domain.dtos.custom_user;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.SafeHtml;

import fr.vilth.sprintplanner.domain.validators.UniqueUsername;

/**
 * {@code Dto} representing a {@code CustomUser}.
 * 
 * @author Thierry VILLEPREUX
 */
public class CustomUserCreateDto {

    @NotBlank(message = "Username cannot be blank")
    @Length(max = 255, message = "Username must not exceed 255 characters")
    @UniqueUsername(message = "username must be unique")
    @SafeHtml
    private String username;

    @NotBlank(message = "password cannot be blank")
    @Length(min = 8, max = 20, message = "password must contain 8 to 20 characters")
    private String password;

    @NotBlank(message = "firstname cannot be blank")
    @Length(max = 255, message = "firstname must not exceed 255 characters")
    @SafeHtml
    private String firstname;

    @NotBlank(message = "lastname cannot be blank")
    @Length(max = 255, message = "lastname must not exceed 255 characters")
    @SafeHtml
    private String lastname;

    protected CustomUserCreateDto() {
	// Empty no-arg constructor
    }

    /**
     * Getter for username
     * 
     * @return ausername
     */
    public String getUsername() {
	return username;
    }

    /**
     * Setter for username
     * 
     * @param username the username to set
     */
    public void setUsername(String username) {
	this.username = username;
    }

    /**
     * Getter for password
     * 
     * @return a password
     */
    public String getPassword() {
	return password;
    }

    /**
     * Setter for password
     * 
     * @param password the password to set
     */
    public void setPassword(String password) {
	this.password = password;
    }

    /**
     * Getter for firstname
     * 
     * @return a firstname
     */
    public String getFirstname() {
	return firstname;
    }

    /**
     * Settet for firstname
     * 
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
	this.firstname = firstname;
    }

    /**
     * Getter for lastname
     * 
     * @return a lastname
     */
    public String getLastname() {
	return lastname;
    }

    /**
     * Setter for lastname
     * 
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
	this.lastname = lastname;
    }

    @Override
    public String toString() {
	return "{username=" + username + ", password=[PROTECTED]}";
    }
}
