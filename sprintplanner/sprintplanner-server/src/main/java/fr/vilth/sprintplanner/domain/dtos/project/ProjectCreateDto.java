package fr.vilth.sprintplanner.domain.dtos.project;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * {@code Dto} representing a {@code Project} to create.
 * 
 * @author Thierry VILLEPREUX
 *
 */
public class ProjectCreateDto {

	@SuppressWarnings("unused") // required for Jackson mapping
	private Long id;

	private String trigram;

	@NotBlank
	private String name;

	@Email
	@NotBlank
	private String email;

	private int piDuration;

	private int sprintDuration;

	@NotBlank
	private String githubUser;

	@NotBlank
	private String githubRepo;

	protected ProjectCreateDto() {
		//
	}

	@Override
	public String toString() {
		return "{trigram=" + trigram + ", name=" + name + ", email=" + email + ", piDuration=" + piDuration
				+ ", sprintDuration=" + sprintDuration + ", githubUser=" + githubUser + ", githubRepo=" + githubRepo
				+ "}";
	}

	/**
	 * Getter for trigram
	 * 
	 * @return the trigram
	 */
	public String getTrigram() {
		return trigram;
	}
}
