package fr.vilth.sprintplanner.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
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

    private static final long serialVersionUID = 6187563238894885084L;

    @Column
    private int pi;

    @Column
    private int sprint;

    @Column
    private int week;

    @Column
    private String versionNumber;

    @Column
    private String releaser;

    protected Release() {
	// empty no-arg constructor
    }
}
