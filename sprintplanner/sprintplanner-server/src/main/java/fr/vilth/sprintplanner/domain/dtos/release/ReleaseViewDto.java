package fr.vilth.sprintplanner.domain.dtos.release;

import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateViewDto;

public class ReleaseViewDto {

    private Long id;

    private int pi;

    private int sprint;

    private int week;

    private CandidateViewDto assignee;

    /**
     * Empty no-arg empty constructor.
     */
    public ReleaseViewDto() {
	//
    }

    public Long getId() {
	return id;
    }

    public int getPi() {
	return pi;
    }

    public int getSprint() {
	return sprint;
    }

    public int getWeek() {
	return week;
    }

    public CandidateViewDto getAssignee() {
	return assignee;
    }

    @Override
    public String toString() {
	return "{id=" + id + ", pi=" + pi + ", sprint=" + sprint + ", week="
		+ week + ", assignee=" + assignee + "}";
    }
}
