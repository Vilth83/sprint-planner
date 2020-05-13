package fr.vilth.sprintplanner.api.controllers;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.vilth.sprintplanner.api.services.ProjectService;
import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.project.ProjectCreateDto;
import fr.vilth.sprintplanner.domain.dtos.project.ProjectViewDto;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
	this.projectService = projectService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public EntityIdDto save(@Valid @RequestBody ProjectCreateDto inputs) {
	return projectService.save(inputs);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ProjectViewDto getProject() {
	return projectService.getProject();
    }
}
