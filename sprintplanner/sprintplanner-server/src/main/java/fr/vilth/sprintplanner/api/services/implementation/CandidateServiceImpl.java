package fr.vilth.sprintplanner.api.services.implementation;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.vilth.sprintplanner.api.repositories.CandidateRepository;
import fr.vilth.sprintplanner.api.services.CandidateService;
import fr.vilth.sprintplanner.commons.api.AbstractService;
import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateCreateDto;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateViewDto;
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
	Long taskId = inputs.getTask().getId();
	List<Candidate> candidates = candidateRepository
		.findAllByTaskId(taskId);
	candidates.forEach(Candidate::incrementPriority);
	Candidate candidate = modelMapper.map(inputs, Candidate.class);
	Candidate candidateId = candidateRepository.save(candidate);
	candidateRepository.saveAll(candidates);
	return modelMapper.map(candidateId, EntityIdDto.class);
    }

    @Override
    public Set<CandidateViewDto> findAllByTask(String taskName) {
	List<Candidate> candidates = candidateRepository
		.findAllByTaskName(taskName);
	return candidates.stream().map(
		candidate -> modelMapper.map(candidate, CandidateViewDto.class))
		.collect(Collectors.toSet());
    }
}
