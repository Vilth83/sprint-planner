package fr.vilth.sprintplanner.domain.dtos.candidate;

import javax.validation.constraints.NotNull;

import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.types.Status;

public class CandidateCreateDto {

    @NotNull
    private EntityIdDto member;

    @NotNull
    private EntityIdDto task;

    private int priority = 0;

    private Status status = Status.AVAILABLE;

    protected CandidateCreateDto() {
	//
    }

    @Override
    public String toString() {
	return "{member=" + member + ", task=" + task + ", priority=" + priority
		+ ", status=" + status + "}";
    }
}
