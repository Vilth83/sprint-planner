package fr.vilth.sprintplanner.api.services;

import java.util.Set;

import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateCreateDto;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateDeleteDto;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateUpdateDto;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateViewDto;

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
     * Returns wether or not a {@code Candidate} exists with given
     * {@code Member} id.
     * 
     * @param id the identifier of a {@code Member}
     * @return {@code true} if a {@code Candidate} exists with given
     *         {@code Member} id; {@code false} otherwise
     */
    boolean existByMemberId(Long id);

    /**
     * Updates given {@code CandidateUpdateDto}.
     * <p>
     * Implementation should retrieve {@code Candidate} by given id, modify it
     * regarding inputs and persists it.
     * 
     * @param inputs the given {@code CandidateUpdateDto}
     * @param id the identifier of the {@code Candidate} to update
     */
    void update(CandidateUpdateDto inputs, Long id);

    /**
     * Delete a {@code CandidateDeleteDto} by his id.
     * <p>
     * 
     * @param candidate the given identifier of the {@code Candidate}
     *        encapsulated in a {@code CandidateDeleteDto}.
     */
    void delete(CandidateDeleteDto candidate);
}
