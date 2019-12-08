package fr.vilth.sprintplanner.api.services;

import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateCreateDto;

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
}
