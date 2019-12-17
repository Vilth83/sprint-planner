package fr.vilth.sprintplanner.domain.dtos.task;

import fr.vilth.sprintplanner.domain.dtos.member.MemberViewDto;

/**
 * A DTO representation of a {@code Task}.
 * <p>
 * this DTO give all informations about a {@code Task}.
 * 
 * @author Thierry VILLEPREUX
 */
public class TaskViewDto {

    private String name;

    private String description;

    private String email;

    private MemberViewDto manager;

    protected TaskViewDto() {
	// protected empty no-arg constructor
    }

    @Override
    public String toString() {
	return "{name=" + name + ", description=" + description + ", email="
		+ email + ", manager=" + manager + "}";
    }
}
