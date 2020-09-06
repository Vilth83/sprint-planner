package fr.vilth.sprintplanner.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import fr.vilth.sprintplanner.configuration.entities.AbstractEntity;
import fr.vilth.sprintplanner.domain.types.Status;

/**
 * A representation of a job {@code Candidate}.
 * <p>
 * Invariants are :
 * <ul>
 * <li>{@code Member} cannot be null
 * <li>{@code Status} cannot be null and must match one of the four existing
 * {@linkplain fr.vilth.sprintplanner.domain.types.Status statuses}
 * <li>{@code Task} cannot be null
 * </ul>
 * 
 * @author Thierry VILLEPREUX
 */
@Entity
@Table(name = "candidates", indexes = {
	@Index(name = "candidates_member_id_IDX", columnList = "member_id"),
	@Index(name = "candidates_task_id_IDX", columnList = "task_id")
}, uniqueConstraints = @UniqueConstraint(name = "candidates_member_id_task_id_UNIQUE", columnNames = {
	"member_id", "task_id" }))
public class Candidate extends AbstractEntity {

    private static final long serialVersionUID = -1843278774867807209L;

    @ManyToOne
    @JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "candidates_member_id_FK"))
    private Member member;

    @Column(nullable = false, columnDefinition = "enum('AVAILABLE', 'UNAVAILABLE', 'CURRENT')")
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "candidates_task_id_FK"))
    @ManyToOne
    private Task task;

    @Column(nullable = false, length = 4)
    private int priority;

    /**
     * Protected no-arg empty constructor
     */
    protected Candidate() {
	//
    }

    /**
     * Increments the priority of this {@code Candidate} by one.
     */
    public void incrementPriority() {
	priority = priority + 1;
    }

    /**
     * Accessor returning {@code priority}.
     * <p>
     * needed by the
     * {@link #fr.vilth.sprintplanner.commons.utils.PriorityComparator
     * PriorityComparator}
     * 
     * @return priority
     */
    public int getPriority() {
	return priority;
    }

    /**
     * Returns the {@code Status} of this {@code Candidate}
     * 
     * @return a {@code Status}
     */
    public Status getStatus() {
	return status;
    }

    /**
     * Make this {@code Candidate} previous candidate.
     * <p>
     * {@code Status} becomes {@code AVAILABLE} and priority is set to 0, so
     * this {@code Candidate} go to queue of the round robin selection.
     */
    public void becomesPrevious() {
	this.status = Status.AVAILABLE;
	this.priority = 0;
    }

    /**
     * Set this {@code Candidate} {@code Status} to given {@code Status}
     * 
     * @param status the {@code Status} to be set
     */
    public void setStatus(Status status) {
	this.status = status;
    }

    /**
     * Set this {@code Candidate} {@code Status} to {@code CURRENT}.
     * <p>
     * Utility method to permit method reference in lambda, to ease readibility.
     */
    public void becomesCurrent() {
	this.status = Status.CURRENT;
    }

    /**
     * Return wether or not this {@code Candidate} {@code Status} is equal to
     * {@code UNAVAILABLE}.
     * <p>
     * Utility method to permit method reference in lambda, to ease readibility.
     * 
     * @return {@code true} if this {@code Candidate} {@code status} is equal to
     *         {@code Status.AVAILABLE}; {@code false} otherwise
     */
    public final boolean isAvailable() {
	return this.status.equals(Status.AVAILABLE);
    }
}
