package fr.vilth.sprintplanner.domain.entities;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

import fr.vilth.sprintplanner.commons.entities.AbstractEntity;
import fr.vilth.sprintplanner.commons.utils.BooleanConverter;

/**
 * Entity representing a {@code Job}.
 * <p>
 * A Job is defined by its title and its task.
 * <ul>
 * <li>title is the main goal of the job (sending a mail, round robin a
 * {@code List} of {@code Candidate}</li>
 * <li>task represents the task concerned by the job (support, release,
 * test)</li>
 * </ul>
 * 
 * @author Thierry VILLEPREUX
 */
@Entity
@Table(name = "jobs")
public class Job extends AbstractEntity {

    private static final long serialVersionUID = 3578977239388137220L;

    @Column(nullable = false, length = 10)
    private String title;

    @Column(nullable = false, length = 10)
    private String task;

    @Column
    @Convert(converter = BooleanConverter.class)
    private boolean active;

    /**
     * Getter for active.
     * <p>
     * Returns wether or not this {@code Job} is active.
     * 
     * @return {@code true} if active; {@code false} otherwise
     */
    public boolean isActive() {
	return active;
    }
}
