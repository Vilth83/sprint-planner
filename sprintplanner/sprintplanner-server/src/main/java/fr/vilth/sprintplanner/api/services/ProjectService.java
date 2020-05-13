package fr.vilth.sprintplanner.api.services;

import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.project.ProjectCreateDto;
import fr.vilth.sprintplanner.domain.dtos.project.ProjectViewDto;

public interface ProjectService {

    EntityIdDto save(ProjectCreateDto inputs);

    ProjectViewDto getProject();
}
