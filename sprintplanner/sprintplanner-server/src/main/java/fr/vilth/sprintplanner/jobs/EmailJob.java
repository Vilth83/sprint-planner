package fr.vilth.sprintplanner.jobs;

import org.springframework.beans.factory.annotation.Autowired;

import fr.vilth.sprintplanner.api.services.CandidateService;
import fr.vilth.sprintplanner.api.services.ProjectService;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateViewDto;
import fr.vilth.sprintplanner.domain.dtos.project.ProjectViewDto;
import fr.vilth.sprintplanner.domain.types.Shift;
import fr.vilth.sprintplanner.domain.types.Status;

public class EmailJob {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private CandidateService candidateService;

    private String getSender() {
	ProjectViewDto project = projectService.getProject();
	return project.getEmail();
    }

    public void buildMail(String taskName) {
	CandidateViewDto candidate = candidateService
		.findFirstByTaskNameAndStatusAndMemberShift(taskName,
			Status.CURRENT,
			Shift.PAR);
    }
}
