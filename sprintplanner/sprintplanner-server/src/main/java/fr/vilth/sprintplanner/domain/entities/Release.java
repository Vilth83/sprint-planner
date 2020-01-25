package fr.vilth.sprintplanner.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.vilth.sprintplanner.commons.entities.AbstractEntity;

@Entity
@Table(name = "release_version")
public class Release extends AbstractEntity {

    private static final long serialVersionUID = 2252928912849412768L;

    @Column
    private int pi;

    @Column
    private int sprint;

    @Column
    private int week;

    @ManyToOne
    private Candidate assignee;

    protected Release() {
	// Empty protected constructor
    }
}
