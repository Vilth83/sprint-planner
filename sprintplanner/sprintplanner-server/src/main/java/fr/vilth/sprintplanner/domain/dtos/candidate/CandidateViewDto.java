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

    private Long id;

    private MemberViewDto member;

    private Status status;

    private TaskViewDto task;

    private int priority;

    protected CandidateViewDto() {
	// protected empty no-arg constructor
    }

    public MemberViewDto getMember() {
	return member;
    }

    @Override
    public String toString() {
	return "{id=" + id + ", member=" + member + ", status=" + status
		+ ", task=" + task + ", priority=" + priority + "}";
    }
}
