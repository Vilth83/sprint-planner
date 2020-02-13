package fr.vilth.sprintplanner.domain.entities;

import javax.persistence.Entity;

import fr.vilth.sprintplanner.commons.entities.AbstractEntity;

@Entity
public class Project extends AbstractEntity {

    private String name;

    private String email;

    private int piDuration;

    private int sprintDuration;

    private String githubUser;

    private String githubRepo;
}
