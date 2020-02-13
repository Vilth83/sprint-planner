package fr.vilth.sprintplanner.domain.dtos.release;

import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;

public class ReleaseCreateDto {

    private int pi;

    private int sprint;

    private int week;

    private EntityIdDto assignee;

    /**
     * Empty no-arg empty constructor.
     */
    protected ReleaseCreateDto() {
	//
    }

    public ReleaseCreateDto(int pi, int sprint, int week) {
	this.pi = pi;
	this.sprint = sprint;
	this.week = week;
    }

    @Override
    public String toString() {
	return "{pi=" + pi + ", sprint=" + sprint + ", week=" + week
		+ ", assignee=" + assignee + "}";
    }
}
