package fr.vilth.sprintplanner.api.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.vilth.sprintplanner.api.services.CandidateService;
import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateCreateDto;

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
}
