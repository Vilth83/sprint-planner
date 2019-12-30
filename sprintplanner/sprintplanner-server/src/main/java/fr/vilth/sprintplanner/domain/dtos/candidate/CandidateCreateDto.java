package fr.vilth.sprintplanner.domain.dtos.candidate;

import javax.validation.constraints.NotNull;

import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.types.Status;

/**
 * DTO representing {@code Candidate} data to be persisted in database.
 * 
 * @author Thierry VILLEPREUX
 */
public class CandidateCreateDto {

    @NotNull
    private EntityIdDto member;

    @NotNull
    private EntityIdDto task;

    private int priority = 0;

    private Status status = Status.AVAILABLE;

    /**
     * Empty no-arg empty constructor.
     */
    protected CandidateCreateDto() {
	//
    }

    /**
     * returns the {@code task} of given {@code CandidateCreateDto}
     * 
     * @return an {@code EntityIdDto} encapsulating the {@code Task} id
     */
    public EntityIdDto getTask() {
	return task;
    }

    @Override
    public String toString() {
	return "{member=" + member + ", task=" + task + ", priority=" + priority
		+ ", status=" + status + "}";
    }
}
