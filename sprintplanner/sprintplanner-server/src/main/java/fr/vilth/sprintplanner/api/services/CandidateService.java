package fr.vilth.sprintplanner.api.services;

import java.util.Set;

import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateCreateDto;
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

    boolean existByMemberId(Long id);
}
