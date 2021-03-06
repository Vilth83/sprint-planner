package fr.vilth.sprintplanner.api.services.implementation;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.vilth.sprintplanner.api.repositories.CandidateJpaRepository;
import fr.vilth.sprintplanner.api.services.CandidateService;
import fr.vilth.sprintplanner.configuration.api.AbstractService;
import fr.vilth.sprintplanner.configuration.exceptions.ResourceNotFoundException;
import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateCreateDto;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateDeleteDto;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateNameDto;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateUpdateDto;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateViewDto;
import fr.vilth.sprintplanner.domain.entities.Candidate;
import fr.vilth.sprintplanner.domain.types.Shift;
import fr.vilth.sprintplanner.domain.types.Status;
import fr.vilth.sprintplanner.domain.utils.RoundRobinHandler;

/**
 * Default concrete implementation of {@code CandidateService}.
 * 
 * @author Thierry VILLEPREUX
 */
@Service
public class CandidateServiceImpl extends AbstractService
	implements CandidateService {

    private final CandidateJpaRepository candidateRepository;

    /**
     * Protected constructor to autowire needed bean.
     * <p>
     * injects {@code CandidateRepository} interface to persist {@code Member}.
     * 
     * @param candidateRepository the injected {@code CandidateRepository}
     */
    public CandidateServiceImpl(CandidateJpaRepository candidateRepository) {
	this.candidateRepository = candidateRepository;
    }

    @Override
    public EntityIdDto save(CandidateCreateDto inputs) {
	Long taskId = inputs.getTask().getId();
	List<Candidate> candidates = candidateRepository
		.findAllByTaskId(taskId);
	candidates.forEach(Candidate::incrementPriority);
	Candidate candidate = convert(inputs, Candidate.class);
	candidates.add(candidate);
	candidateRepository.saveAll(candidates);
	return convert(candidate, EntityIdDto.class);
    }

    @Override
    public boolean existByMemberId(Long id) {
	return candidateRepository.existsByMemberId(id);
    }

    @Override
    public void update(CandidateUpdateDto inputs, Long id) {
	Candidate candidate = candidateRepository.findById(id)
		.orElseThrow(ResourceNotFoundException::new);
	merge(inputs, candidate);
	candidateRepository.save(candidate);
    }

    @Override
    public void delete(CandidateDeleteDto candidate) {
	Candidate deleted = convert(candidate, Candidate.class);
	candidateRepository.delete(deleted);
    }

    @Override
    public void rotateCandidates(Set<CandidateViewDto> inputs) {
	Set<Candidate> candidates = convertSet(inputs, Candidate.class);
	RoundRobinHandler.rotate(candidates);
	candidateRepository.saveAll(candidates);
    }

    @Override
    public CandidateViewDto findFirstByTaskNameAndStatusAndMemberShift(
	    String task, Status status, Shift shift) {
	List<Candidate> candidates = candidateRepository
		.findFirstCandidateByParameters(
			task, status, shift,
			PageRequest.of(0, 1));
	return candidates.stream().findFirst()
		.map(elt -> convert(elt, CandidateViewDto.class))
		.orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Set<CandidateViewDto> findAllByTaskAndShift(String taskName,
	    Shift shift) {
	List<Candidate> candidates = candidateRepository
		.findAllByTaskAndShift(taskName, shift);
	return convertToSet(candidates, CandidateViewDto.class);
    }

    @Override
    public CandidateNameDto findCandidateFullNameByTaskAndStatus(
	    String taskName,
	    Status status) {
	Optional<CandidateNameDto> candidate = candidateRepository
		.findCandidateNameByTaskNameAndStatus(taskName,
			status);
	return candidate.orElse(new CandidateNameDto("no", "candidate"));
    }

    @Override
    @Transactional
    public void setToCurrent(String taskName, CandidateUpdateDto inputs,
	    Long id, Shift shift) {
	List<Candidate> candidates = candidateRepository
		.findFirstCandidateByParameters(
			taskName, Status.CURRENT, shift,
			PageRequest.of(0, 1));
	candidates.stream().findFirst().ifPresent(this::saveAsUnavailable);
	update(inputs, id);
    }

    private void saveAsUnavailable(Candidate candidate) {
	candidate.setStatus(Status.UNAVAILABLE);
	update(modelMapper.map(candidate, CandidateUpdateDto.class),
		candidate.getId());
    }
}
