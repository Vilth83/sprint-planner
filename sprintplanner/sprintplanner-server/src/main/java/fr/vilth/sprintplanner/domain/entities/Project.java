package fr.vilth.sprintplanner.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import fr.vilth.sprintplanner.commons.entities.AbstractEntity;

@Entity
@Table(name = "projects", uniqueConstraints = 
@UniqueConstraint(name = "projects_trigram_UNIQUE", columnNames = "trigram"))
public class Project extends AbstractEntity {

    private static final long serialVersionUID = -2558454895800372791L;

    @Column(nullable = false, length = 5)
    private String trigram;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false)
    private int piDuration;

    @Column(nullable = false)
    private int sprintDuration;

    @Column(nullable = false, length = 100)
    private String githubUser;

    @Column(nullable = false, length = 100)
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
