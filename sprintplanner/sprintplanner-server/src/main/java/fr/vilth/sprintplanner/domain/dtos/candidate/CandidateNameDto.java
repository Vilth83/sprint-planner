package fr.vilth.sprintplanner.domain.dtos.candidate;

/**
 * a {@code Dto} representation of the name of a {@code Candidate}.
 * 
 * @author Thierry VILLEPREUX
 */
public class CandidateNameDto {

    String firstname;

    String lastname;

    public String getFirstname() {
	return firstname;
    }

    public void setFirstname(String firstname) {
	this.firstname = firstname;
    }

    public String getLastname() {
	return lastname;
    }

    public void setLastname(String lastname) {
	this.lastname = lastname;
    }

    protected CandidateNameDto() {
	//
    }

    /**
     * Public constructor returning a new instance of {@code CandidateViewDto}.
     * <p>
     * This constructor is required to retrieve a {@code Dto} using a @Query
     * annotation in
     * {@linkplain fr.vilth.sprintplanner.api.repositories.CandidateJpaRepository#findCandidateNameByTaskNameAndStatus
     * findCandidateNameByTaskNameAndStatus}
     * 
     * @see {@linkplain fr.vilth.sprintplanner.commons.utils.JpqlQuery#CANDIDATE_NAME_QUERY
     *      CANDIDATE_NAME_QUERY}
     * @param firstname
     * @param lastname
     */
    public CandidateNameDto(String firstname, String lastname) {
	this.firstname = firstname;
	this.lastname = lastname;
    }
}
