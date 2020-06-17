package fr.vilth.sprintplanner.domain.dtos.project;

/**
 * {@code Dto} representation of all {@code Project} informations.
 * 
 * @author Thierry VILLEPREUX
 */
public class ProjectViewDto {

    @SuppressWarnings("unused") // required for Jackson and ModelMapper
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
		+ ", piDuration=" + piDuration
		+ ", sprintDuration=" + sprintDuration + ", githubUser="
		+ githubUser + ", githubRepo=" + githubRepo
		+ "}";
    }

    /**
     * Getter for Sprint duration
     * 
     * @return a sprint duration
     */
    public int getSprintDuration() {
	return sprintDuration;
    }

    /**
     * Getter fo piDuration
     * 
     * @return a pi duration
     */
    public int getPiDuration() {
	return piDuration;
    }

    /**
     * Getter for email
     * 
     * @return the sending email
     */
    public String getEmail() {
	return email;
    }

    public String getGithubUser() {
	return githubUser;
    }

    public String getName() {
	return name;
    }

    public String getGithubRepo() {
	return githubRepo;
    }
}
