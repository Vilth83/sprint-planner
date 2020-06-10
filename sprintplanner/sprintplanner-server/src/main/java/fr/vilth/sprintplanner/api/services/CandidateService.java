package fr.vilth.sprintplanner.api.services;

import java.util.Set;

import javax.validation.Valid;

import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateCreateDto;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateDeleteDto;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateNameDto;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateUpdateDto;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateViewDto;
import fr.vilth.sprintplanner.domain.types.Shift;
import fr.vilth.sprintplanner.domain.types.Status;

/**
 * Service to handle {@code Candidate} persistence.
 * 
 * @author Thierry VILLEPREUX
 */
public interface CandidateService {

	/**
	 * Persists given {@code CandidateCreateDto}.
	 * 
	 * @param inputs the given {@code CandidateCreateDto}
	 * @return the attributed {@code id} encapsulated in an {@code EntityIdDto}
	 */
	EntityIdDto save(CandidateCreateDto inputs);

	/**
	 * Returns a {@code Set} of {@code Candidate} by given {@code Task} name.
	 * 
	 * @param taskName the name of the given {@code Task}
	 * @return a {@code Set} of {@code Candidate}
	 */
	Set<CandidateViewDto> findAllByTask(String taskName);

	/**
	 * Returns wether or not a {@code Candidate} exists with given {@code Member}
	 * id.
	 * 
	 * @param id the identifier of a {@code Member}
	 * @return {@code true} if a {@code Candidate} exists with given {@code Member}
	 *         id; {@code false} otherwise
	 */
	boolean existByMemberId(Long id);

	/**
	 * Updates given {@code CandidateUpdateDto}.
	 * <p>
	 * Implementation should retrieve {@code Candidate} by given id, modify it
	 * regarding inputs and persists it.
	 * 
	 * @param inputs the given {@code CandidateUpdateDto}
	 * @param id     the identifier of the {@code Candidate} to update
	 */
	void update(CandidateUpdateDto inputs, Long id);

	/**
	 * Delete a {@code CandidateDeleteDto} by his id.
	 * <p>
	 * 
	 * @param candidate the given identifier of the {@code Candidate} encapsulated
	 *                  in a {@code CandidateDeleteDto}.
	 */
	void delete(CandidateDeleteDto candidate);

	/**
	 * Rotate {@code Candidate} in the input with Round Robin algorithm and save the
	 * new {@code Set}
	 * 
	 * @param inputs the {@code Set} of {@code Candidate}
	 */
	void rotateCandidates(Set<CandidateViewDto> inputs);

	/**
	 * Retrieve one element by its task, {@code Status} and {@code Shift}
	 * <p>
	 * This method intends to find one and only one element.
	 * 
	 * @param task   the name of the task
	 * @param status the given {@code Status}
	 * @param shift  the given {@code Shift}
	 * @return a {@code CandidateViewDto} representing a {@code Candidate}
	 */
	CandidateViewDto findFirstByTaskNameAndStatusAndMemberShift(String task, Status status, Shift shift);

	/**
	 * Finds all {@code Candidate} having given task and {@code Shift}
	 * 
	 * @param taskName the name of the given task
	 * @param shift    the given {@code Shift}
	 * @return a {@code Set} of {@code CandidateViewDto}
	 */
	Set<CandidateViewDto> findAllByTaskAndShift(String taskName, Shift shift);

	/**
	 * Returns the {@code Candidate} for given task and with given {@code Status}
	 * full name in the form of a {@code CandidateNameDto}
	 * 
	 * @param taskName the given task
	 * @param status   the given {@code Status}
	 * @return a {@code CandidateViewDto}
	 */
	CandidateNameDto findCandidateFullNameByTaskAndStatus(String taskName, Status status);

	/**
	 * Set {@code Candidate} with given id to {@code Status.CURRENT} candidate for
	 * given task
	 * 
	 * @param taskName the task concerned by the candidate
	 * @param inputs   a {@code DTO} representation containing the modifications
	 * @param id       the id of the {@code Candidate}
	 * @param shift    optional : if given, the shift of the candidate to modify
	 */
	void setToCurrent(String taskName, @Valid CandidateUpdateDto inputs, Long id, Shift shift);
}
