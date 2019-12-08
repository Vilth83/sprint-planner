package fr.vilth.sprintplanner.api.services.implementation;

import org.springframework.stereotype.Service;

import fr.vilth.sprintplanner.api.repositories.CandidateRepository;
import fr.vilth.sprintplanner.api.services.CandidateService;
import fr.vilth.sprintplanner.commons.api.AbstractService;
import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateCreateDto;
import fr.vilth.sprintplanner.domain.entities.Candidate;

/**
 * Default concrete implementation of {@code CandidateService}.
 * 
 * @author Thierry VILLEPREUX
 */
@Service
public class CandidateServiceImpl extends AbstractService
	implements CandidateService {

    private final CandidateRepository candidateRepository;

    /**
     * Protected constructor to autowire needed bean.
     * <p>
     * injects {@code CandidateRepository} interface to persist {@code Member}.
     * 
     * @param candidateRepository the injected {@code CandidateRepository}
     */
    public CandidateServiceImpl(CandidateRepository candidateRepository) {
	this.candidateRepository = candidateRepository;
    }

    @Override
    public EntityIdDto save(CandidateCreateDto inputs) {
	Candidate candidate = modelMapper.map(inputs, Candidate.class);
	Candidate candidateId = candidateRepository.save(candidate);
	return modelMapper.map(candidateId, EntityIdDto.class);
    }
}
