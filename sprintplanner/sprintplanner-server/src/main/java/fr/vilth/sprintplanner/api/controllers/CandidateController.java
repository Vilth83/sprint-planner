package fr.vilth.sprintplanner.api.controllers;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.vilth.sprintplanner.api.services.CandidateService;
import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateCreateDto;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateViewDto;

/**
 * a {@code RestController} to handle {@code Candidate}.
 * 
 * @author Thierry VILLEPREUX
 */
@RestController
@RequestMapping("/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    /**
     * Protected constructor to autowire needed bean.
     * <p>
     * injects {@code CandidateService} interface
     * 
     * @param candidateService the injected {@code CandidateService}.
     */
    protected CandidateController(CandidateService candidateService) {
	this.candidateService = candidateService;
    }

    /**
     * Persists a {@code CandidateCreateDto}.
     *
     * @param inputs the {@code candidateCreateDto} to persist.
     * @return the attributed id encapsulated in a {@code EntityIdDto}.
     */
    @PostMapping
    public EntityIdDto save(@Valid @RequestBody CandidateCreateDto inputs) {
	return candidateService.save(inputs);
    }

    /**
     * Returns a {@code Set} of {@code Candidate} by given {@code Task} name.
     * 
     * @param taskName the name of the given {@code Task}
     * @return a {@code Set} of {@code Candidate}
     */
    @GetMapping("/{taskName}")
    public Set<CandidateViewDto> findAllByTaskName(
	    @PathVariable String taskName) {
	return candidateService.findAllByTask(taskName);
    }
}
