package fr.vilth.sprintplanner.api.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.vilth.sprintplanner.api.services.CandidateService;
import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateCreateDto;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    protected CandidateController(CandidateService candidateService) {
	this.candidateService = candidateService;
    }

    @PostMapping
    public EntityIdDto save(@Valid @RequestBody CandidateCreateDto inputs) {
	return candidateService.save(inputs);
    }
}
