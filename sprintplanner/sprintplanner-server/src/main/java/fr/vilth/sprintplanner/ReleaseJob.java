package fr.vilth.sprintplanner;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fr.vilth.sprintplanner.api.services.CandidateService;
import fr.vilth.sprintplanner.api.services.ProjectService;
import fr.vilth.sprintplanner.api.services.ReleaseService;
import fr.vilth.sprintplanner.commons.api.AbstractService;
import fr.vilth.sprintplanner.domain.dtos.project.ProjectViewDto;
import fr.vilth.sprintplanner.domain.entities.Project;

@Service
public class ReleaseJob extends AbstractService {

    private final ReleaseService releaseService;

    private final ProjectService projectService;

    private final CandidateService candidateService;

    public ReleaseJob(ReleaseService releaseService,
	    ProjectService projectService, CandidateService candidateService) {
	this.releaseService = releaseService;
	this.projectService = projectService;
	this.candidateService = candidateService;
    }

    @Scheduled(cron = "${cron.config.increment-version}")
    public void incrementReleaseVersion() {
	ProjectViewDto projectDto = projectService.getProject();
	Project project = modelMapper.map(projectDto, Project.class);
	releaseService.incrementReleaseVersion(project);
    }
}
