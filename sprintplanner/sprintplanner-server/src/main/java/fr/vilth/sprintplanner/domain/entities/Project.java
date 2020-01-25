package fr.vilth.sprintplanner.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

import fr.vilth.sprintplanner.commons.entities.AbstractEntity;

@Entity
public class Project extends AbstractEntity {

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private int piDuration;

    @Column
    private int sprintDuration;

    @Column
    private String githubUser;

    @Column
    private String defaultRepository;

    public String getName() {
	return name;
    }

    public String getEmail() {
	return email;
    }

    public int getPiDuration() {
	return piDuration;
    }

    public int getSprintDuration() {
	return sprintDuration;
    }

    public String getGithubUser() {
	return githubUser;
    }

    public String getDefaultRepository() {
	return defaultRepository;
    }
}
