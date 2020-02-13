package fr.vilth.sprintplanner.domain.dtos.project;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class ProjectCreateDto {

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
}
