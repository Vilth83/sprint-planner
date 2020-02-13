package fr.vilth.sprintplanner.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.vilth.sprintplanner.commons.entities.AbstractEntity;

/**
 * Representation of a {@code Release}.
 * <p>
 * A release contains a version number, determined by a pi, a sprint and a week
 * number. It is done by a {@code Candidate}
 * 
 * @author Thierry VILLEPREUX
 */
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

    public final Release incrementRelease(Project project) {
	week++;
	if (week > project.getSprintDuration()) {
	    week = 1;
	    sprint++;
	    if (sprint > project.getPiDuration()) {
		sprint = 1;
		pi++;
	    }
	}
	return this;
    }
}
