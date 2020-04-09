package fr.vilth.sprintplanner.api.services.implementation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import fr.vilth.sprintplanner.api.repositories.ProjectJpaRepository;
import fr.vilth.sprintplanner.api.services.ProjectService;
import fr.vilth.sprintplanner.commons.api.AbstractService;
import fr.vilth.sprintplanner.commons.exceptions.ResourceNotFoundException;
import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.project.ProjectCreateDto;
import fr.vilth.sprintplanner.domain.dtos.project.ProjectViewDto;
import fr.vilth.sprintplanner.domain.entities.Project;

@Service
public class ProjectServiceImpl extends AbstractService
	implements ProjectService {

    private final ProjectJpaRepository projectRepository;

    public ProjectServiceImpl(ProjectJpaRepository projectRepository) {
	this.projectRepository = projectRepository;
    }

    @Override
    public EntityIdDto save(@Valid ProjectCreateDto inputs) {
	Project project = projectRepository
		.findByTrigram(inputs.getTrigram());
	modelMapper.map(inputs, project);
	project = projectRepository.save(project);
	return modelMapper.map(project, EntityIdDto.class);
    }

    @Override
    public ProjectViewDto getProject() {
	List<Project> projects = projectRepository.findAll();
	Project project = projects.stream().findFirst()
		.orElseThrow(ResourceNotFoundException::new);
	return modelMapper.map(project, ProjectViewDto.class);
    }
}
