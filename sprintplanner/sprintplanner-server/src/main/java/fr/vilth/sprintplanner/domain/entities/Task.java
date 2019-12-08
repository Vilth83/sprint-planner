package fr.vilth.sprintplanner.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

import fr.vilth.sprintplanner.commons.entities.AbstractEntity;

/**
 * Representation of a {@code Task}.
 * <p>
 * A task is an action fullfilled by a {@code Candidate} at a certain time.
 * 
 * @author Thierry VILLEPREUX
 */
@Entity
public class Task extends AbstractEntity {

    private static final long serialVersionUID = -3742414978040482584L;

    /**
     * the name of a given {@code Task}.
     */
    @Column(nullable = false, length = 10)
    private String name;

    /**
     * the description of a given {@code Task}
     */
    @Column(nullable = true, length = 255)
    private String description;

    /**
     * Protected empty no-arg constructor
     */
    protected Task() {
	//
    }
}
