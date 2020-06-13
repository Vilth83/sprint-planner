package fr.vilth.sprintplanner.api.repositories;

/**
 * Utility class providing Jpql Queries.
 * <p>
 * Ease readibility of repositories and mutualize queries to facilitate their
 * management.
 * 
 * @author Thierry VILLEPREUX
 */
public final class JpqlQuery {

    private JpqlQuery() {
	// private constructor to ensure non-instanciability.
    }

    /**
     * Retrieve a {@code DTO} representing a {@code Candidate} full name
     * <p>
     * Query written to retrieve a candidate full name based on its
     * {@code Status} and given task. Used by
     * {@link CandidateJpaRepository#findCandidateNameByTaskNameAndStatus
     * findCandidateNameByTaskNameAndStatus}
     */
    static final String CANDIDATE_NAME_QUERY = // -
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
    static final String FIRST_ELIGIBLE_CANDIDATE = "select c from Member m join Candidate c on m.id = c.member "
	    + "join Task t on t.id = c.task where t.name = :task and c.status = :status and (:shift is null "
	    + "or m.shift = :shift) order by c.priority desc";
}
