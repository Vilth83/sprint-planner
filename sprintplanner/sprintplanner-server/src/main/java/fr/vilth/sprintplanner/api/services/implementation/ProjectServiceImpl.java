package fr.vilth.sprintplanner.api.services.implementation;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import fr.vilth.sprintplanner.api.repositories.ProjectRepository;
import fr.vilth.sprintplanner.api.services.ProjectService;
import fr.vilth.sprintplanner.commons.api.AbstractService;
import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.project.ProjectCreateDto;
import fr.vilth.sprintplanner.domain.entities.Project;

@Service
public class ProjectServiceImpl extends AbstractService
	implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
	this.projectRepository = projectRepository;
    }

    @Override
    public EntityIdDto save(@Valid ProjectCreateDto inputs) {
	Project project = modelMapper.map(inputs, Project.class);
	project = projectRepository.save(project);
	return modelMapper.map(project, EntityIdDto.class);
    }
}
