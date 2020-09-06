package fr.vilth.sprintplanner.api.services.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.vilth.sprintplanner.api.repositories.ProjectJpaRepository;
import fr.vilth.sprintplanner.api.services.ProjectService;
import fr.vilth.sprintplanner.configuration.api.AbstractService;
import fr.vilth.sprintplanner.configuration.exceptions.ResourceNotFoundException;
import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.project.ProjectCreateDto;
import fr.vilth.sprintplanner.domain.dtos.project.ProjectViewDto;
import fr.vilth.sprintplanner.domain.entities.Project;

/**
 * Implementation of {@code ProjectService}
 * 
 * @author Thierry VILLEPREUX
 */
@Service
public class ProjectServiceImpl extends AbstractService
	implements ProjectService {

    private final ProjectJpaRepository projectRepository;

    /**
     * Protected constructor to autowire needed beans
     * 
     * @param projectRepository injected {@code PorjectRepository}
     */
    protected ProjectServiceImpl(ProjectJpaRepository projectRepository) {
	this.projectRepository = projectRepository;
    }

    /**
     * Persists given {@code ProjectCreateDto} as a {@code Project}.
     * <p>
     * As the application handles only one project for the time being, project
     * is retrieved by its trigram and modifications are mapped with
     * {@code ModelMapper} to the retrieved entity.
     */
    @Override
    public EntityIdDto save(ProjectCreateDto inputs) {
	Project project = projectRepository.findByTrigram(inputs.getTrigram())
		.orElseThrow(ResourceNotFoundException::new);
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
