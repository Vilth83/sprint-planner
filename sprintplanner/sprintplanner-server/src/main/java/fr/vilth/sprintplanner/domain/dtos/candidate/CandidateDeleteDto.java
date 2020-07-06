package fr.vilth.sprintplanner.domain.dtos.candidate;

import javax.validation.constraints.NotNull;

/**
 * DTO encapsulating a {@code Candidate} identifier.
 * 
 * @author Thierry VILLEPREUX
 */
public class CandidateDeleteDto {

    @NotNull
    private Long id;

    @Override
    public String toString() {
	return "{id=" + id + "}";
    }
}
