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

import fr.vilth.sprintplanner.commons.entities.AbstractEntity;
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
     * {@linkplain #fr.vilth.sprintplanner.commons.utils.PriorityComparator
     * PriorityComparator}
     * 
     * @return priority
     */
    public int getPriority() {
	return priority;
    }

    public Status getStatus() {
	return status;
    }

    public void becomesPrevious() {
	this.status = Status.AVAILABLE;
	this.priority = 0;
    }

    public void becomesAvailable() {
	this.status = Status.AVAILABLE;
    }

    public void setStatus(Status status) {
	this.status = status;
    }

    public void becomesCurrent() {
	this.status = Status.CURRENT;
    }

    public void becomesUnavailable() {
	this.status = Status.UNAVAILABLE;
    }

    public final boolean isAvailable() {
	return this.status.equals(Status.AVAILABLE);
    }
}
