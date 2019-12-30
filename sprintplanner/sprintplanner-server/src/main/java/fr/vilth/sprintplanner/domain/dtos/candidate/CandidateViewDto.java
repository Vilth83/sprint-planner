package fr.vilth.sprintplanner.domain.dtos.candidate;

import fr.vilth.sprintplanner.domain.dtos.member.MemberViewDto;
import fr.vilth.sprintplanner.domain.dtos.task.TaskViewDto;
import fr.vilth.sprintplanner.domain.types.Status;

/**
 * A DTO representation of a {@code Candidate}.
 * <p>
 * this DTO give all informations about a {@code Candidate}.
 * 
 * @author Thierry VILLEPREUX
 */
public class CandidateViewDto {

    private MemberViewDto member;

    private Status status;

    private TaskViewDto task;

    private int priority;

    protected CandidateViewDto() {
	// protected empty no-arg constructor
    }

    @Override
    public String toString() {
	return "{member=" + member + ", status=" + status + ", task=" + task
		+ ", priority=" + priority + "}";
    }
}
