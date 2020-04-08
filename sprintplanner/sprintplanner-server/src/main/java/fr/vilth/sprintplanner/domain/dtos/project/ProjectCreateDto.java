package fr.vilth.sprintplanner.domain.dtos.project;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class ProjectCreateDto {

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
	return "{trigram=" + trigram + ", name=" + name + ", email=" + email
		+ ", piDuration=" + piDuration + ", sprintDuration="
		+ sprintDuration + ", githubUser=" + githubUser
		+ ", githubRepo=" + githubRepo + "}";
    }
}
