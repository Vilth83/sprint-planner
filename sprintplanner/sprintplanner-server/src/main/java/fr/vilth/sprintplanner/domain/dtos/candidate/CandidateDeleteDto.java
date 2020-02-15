package fr.vilth.sprintplanner.domain.dtos.candidate;

/**
 * DTO encapsulating a {@code Candidate} identifier.
 * 
 * @author Thierry VILLEPREUX
 */
public class CandidateDeleteDto {

    private Long id;

    @Override
    public String toString() {
	return "{id=" + id + "}";
    }
}
