package fr.vilth.sprintplanner.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
 *
 * @author Thierry VILLEPREUX
 */
@Entity
public class Candidate extends AbstractEntity {

    private static final long serialVersionUID = -1843278774867807209L;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Member member;

    @Column(nullable = false, length = 11)
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @JoinColumn(nullable = false)
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
}
