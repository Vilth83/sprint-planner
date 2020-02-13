package fr.vilth.sprintplanner.api.services;

import javax.validation.Valid;

import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.project.ProjectCreateDto;

public interface ProjectService {

    EntityIdDto save(@Valid ProjectCreateDto inputs);
}
