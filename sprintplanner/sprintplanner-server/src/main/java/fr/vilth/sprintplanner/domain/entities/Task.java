package fr.vilth.sprintplanner.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = true, length = 255)
    private String description;

    @Column(nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Member manager;

    protected Task() {
	//
    }
}
