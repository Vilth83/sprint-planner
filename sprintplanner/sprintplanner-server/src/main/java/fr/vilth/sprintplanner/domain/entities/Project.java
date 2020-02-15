package fr.vilth.sprintplanner.domain.entities;

import javax.persistence.Entity;

import fr.vilth.sprintplanner.commons.entities.AbstractEntity;

@Entity
public class Project extends AbstractEntity {

    private static final long serialVersionUID = -2558454895800372791L;

    private String name;

    private String email;

    private int piDuration;

    private int sprintDuration;

    private String githubUser;

    private String githubRepo;

    protected Project() {
	//
    }

    public int getPiDuration() {
	return piDuration;
    }

    public int getSprintDuration() {
	return sprintDuration;
    }

    @Override
    public String toString() {
	return "{name=" + name + ", email=" + email + ", piDuration="
		+ piDuration + ", sprintDuration=" + sprintDuration
		+ ", githubUser=" + githubUser + ", githubRepo=" + githubRepo
		+ "}";
    }
}
