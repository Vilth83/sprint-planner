package fr.vilth.sprintplanner.commons.utils;

/**
 * Utility class providing Jpql Queries.
 * <p>
 * Ease readibility of repositories and mutualize queries to facilitate their
 * management.
 * 
 * @author Thierry VILLEPREUX
 */
public class JpqlQuery {

    /**
     * Retrieve a {@code DTO} representing a {@code Candidate} full name
     * <p>
     * Query written to retrieace a candidate full name based on its
     * {@code Status} and given task. Used by
     * {@linkplain fr.vilth.sprintplanner.api.repositories.CandidateJpaRepository#findCandidateNameByTaskNameAndStatus(String, fr.vilth.sprintplanner.domain.types.Status)
     * findCandidateNameByTaskNameAndStatus}
     */
    public static final String CANDIDATE_NAME_QUERY = // -
	    "select new fr.vilth.sprintplanner.domain.dtos.candidate.CandidateNameDto(m.firstname, m.lastname) "
		    + "from Member m join Candidate c on m.id = c.member join Task t on t.id = c.task "
		    + "where t.name = :taskName and c.status = :status";

    /**
     * Retrieve a {@code Candidate} by its task, shift and status.
     * <p>
     * shift is optional. If not provided, the {@code Candidate} will be
     * retrieved by its task and status.
     * <p>
     */
    // This method could be written with {@code Spring JPA} derived queries, but
    // a standard query is more readable
    public static final String FIRST_ELIGIBLE_CANDIDATE = "select c from Member m join Candidate c on m.id = c.member "
	    + "join Task t on t.id = c.task where t.name = :task and c.status = :status and (:shift is null "
	    + "or m.shift = :shift) order by c.priority desc";
}
