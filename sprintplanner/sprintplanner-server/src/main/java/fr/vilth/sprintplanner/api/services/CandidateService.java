package fr.vilth.sprintplanner.api.services;

import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateCreateDto;

public interface CandidateService {

    EntityIdDto save(CandidateCreateDto inputs);
}
