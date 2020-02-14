package fr.vilth.sprintplanner.api.services.implementation;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import fr.vilth.sprintplanner.api.repositories.CandidateRepository;
import fr.vilth.sprintplanner.api.services.CandidateService;
import fr.vilth.sprintplanner.commons.api.AbstractService;
import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateCreateDto;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateDeleteDto;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateUpdateDto;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateViewDto;
import fr.vilth.sprintplanner.domain.entities.Candidate;
import fr.vilth.sprintplanner.domain.types.Status;

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
	Candidate candidate = convert(inputs, Candidate.class);
	Candidate candidateId = candidateRepository.save(candidate);
	candidateRepository.saveAll(candidates);
	return convert(candidateId, EntityIdDto.class);
    }

    @Override
    public Set<CandidateViewDto> findAllByTask(String taskName) {
	List<Candidate> candidates = candidateRepository
		.findAllByTaskName(taskName);
	return convertToSet(candidates, CandidateViewDto.class);
    }

    @Override
    public boolean existByMemberId(Long id) {
	return candidateRepository.existsByMemberId(id);
    }

    @Override
    public void update(CandidateUpdateDto inputs, Long id) {
	Candidate candidate = candidateRepository.findById(id).get();
	modelMapper.map(inputs, candidate);
	candidateRepository.save(candidate);
    }

    @Override
    public void delete(CandidateDeleteDto candidate) {
	Candidate deleted = convert(candidate, Candidate.class);
	candidateRepository.delete(deleted);
    }

    @Override
    public CandidateViewDto findFirstByTaskNameAndStatus(String taskName,
	    Status status) {
	return candidateRepository.findFirstBytaskNameAndStatus(taskName,
		status);
    }
}
