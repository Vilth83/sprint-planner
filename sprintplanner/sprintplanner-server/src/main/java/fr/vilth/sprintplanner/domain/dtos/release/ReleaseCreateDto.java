package fr.vilth.sprintplanner.domain.dtos.release;

import javax.validation.constraints.NotNull;

import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;

public class ReleaseCreateDto {

    private int pi;

    private int sprint;

    private int week;

    @NotNull
    private EntityIdDto assignee;

    /**
     * Empty no-arg empty constructor.
     */
    protected ReleaseCreateDto() {
	//
    }

    @Override
    public String toString() {
	return "{pi=" + pi + ", sprint=" + sprint + ", week=" + week
		+ ", assignee=" + assignee + "}";
    }
}
