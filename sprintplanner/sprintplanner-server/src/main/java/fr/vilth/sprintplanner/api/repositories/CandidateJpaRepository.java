package fr.vilth.sprintplanner.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.vilth.sprintplanner.commons.utils.JpqlQuery;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateNameDto;
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

    /**
     * Returns a {@code List} of {@code Candidate}s by given task and
     * {@code Shift}.
     * 
     * @param taskName given task name
     * @param shift given {@code Shift}
     * @return a {@code List} of {@code Candidate}
     */
    List<Candidate> findAllByTaskNameAndMemberShift(String taskName,
	    Shift shift);

    /**
     * Returns the {@code Candidate} with the highest priority for given
     * {@code task}, {@code Status} and {@code Shift}.
     * <p>
     * Return type is a pageable list with only one or zero elements, to be sure
     * to obtain the highest priority member. This is ensured by the order of
     * the return (Priority DESC).
     * 
     * @param task the {@code Task} name
     * @param status the {@code Status}
     * @param shift the {@code Shift} (PAR or BGL)
     * @param pageable a PageRequest of 1 element.
     * @return A {@code List} containing 0 or 1 candidate
     */
    @Query(JpqlQuery.FIRST_ELIGIBLE_CANDIDATE)
    List<Candidate> findFirstCandidateByParameters(
	    @Param("task") String task,
	    @Param("status") Status status,
	    @Param("shift") Shift shift,
	    Pageable pageable);

    /**
     * Returns the {@code Candidate} for given task and with given
     * {@code Status} full name in the form of a {@code CandidateNameDto}
     * 
     * @param taskName the given task
     * @param status the given {@code Status}
     * @return a {@code CandidateViexwDto}
     */
    @Query(JpqlQuery.CANDIDATE_NAME_QUERY)
    Optional<CandidateNameDto> findCandidateNameByTaskNameAndStatus(
	    String taskName,
	    Status status);
}
