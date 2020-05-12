package fr.vilth.sprintplanner.domain.dtos.project;

public class ProjectViewDto {

    private Long id;

    private String trigram;

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
	return "{trigram=" + trigram + ", name=" + name + ", email=" + email
		+ ", piDuration=" + piDuration + ", sprintDuration="
		+ sprintDuration + ", githubUser=" + githubUser
		+ ", githubRepo=" + githubRepo + "}";
    }

    public int getSprintDuration() {
	return sprintDuration;
    }

    public int getPiDuration() {
	return piDuration;
    }

    public String getEmail() {
	return email;
    }
}
