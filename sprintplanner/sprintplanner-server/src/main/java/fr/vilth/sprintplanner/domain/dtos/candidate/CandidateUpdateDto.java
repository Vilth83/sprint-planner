package fr.vilth.sprintplanner.domain.dtos.candidate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import fr.vilth.sprintplanner.domain.types.Status;

/**
 * DTO representing informations to update in a {@code Candidate}.
 * 
 * @author Thierry VILLEPREUX
 */
public class CandidateUpdateDto {

    @NotNull
    private Long id;

    @Min(0)
    @Max(99)
    private int priority;

    @NotNull
    private Status status;

    @Override
    public String toString() {
	return "{id=" + id + ", priority=" + priority + ", status=" + status
		+ "}";
    }
}
