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
    protected ReleaseViewDto() {
	//
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

    @Override
    public String toString() {
	return "{id=" + id + ", pi=" + pi + ", sprint=" + sprint + ", week="
		+ week + ", assignee=" + assignee + "}";
    }
}
