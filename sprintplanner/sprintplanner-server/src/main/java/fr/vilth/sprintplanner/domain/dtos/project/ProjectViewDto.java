package fr.vilth.sprintplanner.domain.dtos.project;

public class ProjectViewDto {

    private String name;

    private String email;

    private int piDuration;

    private int sprintDuration;

    private String githubUser;

    private String githubRepo;

    protected ProjectViewDto() {
	//
    }

    @Override
    public String toString() {
	return "{name=" + name + ", email=" + email + ", piDuration="
		+ piDuration + ", sprintDuration=" + sprintDuration
		+ ", githubUser=" + githubUser + ", githubRepo=" + githubRepo
		+ "}";
    }
}
