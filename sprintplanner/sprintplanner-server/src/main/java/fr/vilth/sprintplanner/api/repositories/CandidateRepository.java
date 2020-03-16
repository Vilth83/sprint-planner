package fr.vilth.sprintplanner.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.vilth.sprintplanner.domain.entities.Candidate;
import fr.vilth.sprintplanner.domain.types.Shift;
import fr.vilth.sprintplanner.domain.types.Status;

/**
 * {@code JpaRepository} to handle {@code Candidate} persistence.
 * 
 * @author Thierry VILLEPREUX
 */
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

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

    Optional<Candidate> findFirstBytaskNameAndStatus(String taskName,
	    Status status);

    List<Candidate> findAllByTaskNameAndMemberShift(String taskName,
	    Shift shift);

    Candidate findFirstByTaskNameAndStatusAndMemberShift(String task,
	    Status current, Shift shift);

    @Query("select concat(m.firstname, ' ', m.lastname) from Member m "
	    + "join Candidate c on m.id = c.member "
	    + "join Task t on t.id = c.task "
	    + "where t.name = :taskName and c.status = :status")
    String findMemberNameByTaskAndStatus(String taskName, Status status);
}
