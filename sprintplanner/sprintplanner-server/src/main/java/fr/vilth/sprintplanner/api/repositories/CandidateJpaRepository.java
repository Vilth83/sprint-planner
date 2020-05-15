package fr.vilth.sprintplanner.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.vilth.sprintplanner.domain.entities.Candidate;
import fr.vilth.sprintplanner.domain.types.Shift;
import fr.vilth.sprintplanner.domain.types.Status;

/**
 * {@code JpaRepository} to handle {@code Candidate} persistence.
 * 
 * @author Thierry VILLEPREUX
 */
public interface CandidateJpaRepository extends JpaRepository<Candidate, Long> {

    /**
     * Returns a {@code List} of {@code Candidate} for the given {@code Task}.
     * 
     * @param taskName the name of the given {@code Task}
     * @return a {@code List} of {@code Candidate}
     */
    List<Candidate> findAllByTaskName(String taskName);

    /**
     * Returns a {@code List} of {@code Candidate} for the given {@code Task}.
     * 
     * @param taskId the id of the given {@code Task}
     * @return a {@code List} of {@code Candidate}
     */
    List<Candidate> findAllByTaskId(Long taskId);

    /**
     * Returns wether or not a {@code Candidate} exists with given
     * {@code Member} id.
     * 
     * @param id the identifier of a {@code Member}
     * @return {@code true} if a {@code Candidate} exists with given
     *         {@code Member} id; {@code false} otherwise
     */
    boolean existsByMemberId(Long id);

    List<Candidate> findAllByTaskNameAndMemberShift(String taskName,
	    Shift shift);

    /**
     * @param task
     * @param status
     * @param shift
     * @param pageable
     * @return A {@code List} containing 0 or 1 candidate
     */
    @Query("select c "
	    + "from Member m join Candidate c on m.id = c.member "
	    + "join Task t on t.id = c.task where t.name = :task "
	    + "and c.status = :status and (:shift is null "
	    + "or m.shift = :shift)"
	    + "order by c.priority desc")
    List<Candidate> findFirstCandidateByParameters(
	    @Param("task") String task,
	    @Param("status") Status status,
	    @Param("shift") Shift shift,
	    Pageable pageable);

    // @Query("select new
    // fr.vilth.sprintplanner.domain.dtos.candidate.CandidateNameDto(m.firstname,
    // m.lastname) "
    // + "from Member m "
    // + "join Candidate c on m.id = c.member "
    // + "join Task t on t.id = c.task "
    // + "where t.name = :taskName and c.status = :status")
    Optional<Candidate> findMemberNameByTaskNameAndStatus(String taskName,
	    Status status);
}
