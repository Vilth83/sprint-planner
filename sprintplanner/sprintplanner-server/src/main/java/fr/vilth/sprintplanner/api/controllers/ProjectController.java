package fr.vilth.sprintplanner.api.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.vilth.sprintplanner.api.services.ProjectService;
import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.project.ProjectCreateDto;
import fr.vilth.sprintplanner.domain.dtos.project.ProjectViewDto;

/**
 * a {@code RestController} to handle {@code Project}
 * 
 * @author Thierry VILLEPREUX
 */
@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    /**
     * Protected constructor injecting needed beans.
     * 
     * @param projectService injected {@code Project Service}
     */
    protected ProjectController(ProjectService projectService) {
	this.projectService = projectService;
    }

    /**
     * Persists given {@code Project}.
     *
     * @param inputs a {@code ProjectCreateDto}, reprensenting a {@code Project}
     * @return a {@code Dto} representation of the returned id
     */
    @PostMapping
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public EntityIdDto save(@Valid @RequestBody ProjectCreateDto inputs) {
	return projectService.save(inputs);
    }

    /**
     * Returns the project informations.
     * <p>
     * As the application only deals with one project for the time being, no id
     * is needed to retrieve the project. In a future release, if this behavior
     * has to change, this method will change to take the trigram of the needed
     * project.
     * 
     * @return the {@code Project}
     */
    @GetMapping
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ProjectViewDto getProject() {
	return projectService.getProject();
    }
}
