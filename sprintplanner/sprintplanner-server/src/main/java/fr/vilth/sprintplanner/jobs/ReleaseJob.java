package fr.vilth.sprintplanner.jobs;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fr.vilth.sprintplanner.api.services.CandidateService;
import fr.vilth.sprintplanner.api.services.JobService;
import fr.vilth.sprintplanner.api.services.ReleaseService;
import fr.vilth.sprintplanner.commons.api.AbstractService;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateViewDto;
import fr.vilth.sprintplanner.domain.entities.Job;
import fr.vilth.sprintplanner.domain.types.Shift;

@Service
public class ReleaseJob extends AbstractService {

    private final ReleaseService releaseService;

    private final CandidateService candidateService;

    private final JobService jobService;

    private static final String RELEASER = "releaser";

    private static final String TESTER = "tester";

    private static final String SUPPORT = "support";

    private static final String TECHNICAL = "technical";

    private static final String FUNCTIONAL = "functional";

    public ReleaseJob(ReleaseService releaseService,
	    CandidateService candidateService,
	    JobService jobService) {
	super();
	this.releaseService = releaseService;
	this.candidateService = candidateService;
	this.jobService = jobService;
    }

    @Scheduled(cron = "${cron.config.handle-release}")
    public void handleRelease() {
	Job job = jobService.findByTitleAndTask("roundRobin", RELEASER);
	// first, the releaser is changed
	if (job.isActive()) {
	    rotateReleasers();
	}
	// then, the release is incremented
	incrementReleaseVersion();
    }

    @Scheduled(cron = "${cron.config.handle-support}")
    public void handleSupport() {
	// the support is changed for each shift and task
	Job job = jobService.findByTitleAndTask("roundRobin", SUPPORT);
	if (job.isActive()) {
	    rotateSupport(TECHNICAL);
	    rotateSupport(FUNCTIONAL);
	}
    }

    private void rotateSupport(String task) {
	List<Shift> shifts = Arrays.asList(Shift.BGL, Shift.PAR);
	shifts.forEach(shift -> {
	    Set<CandidateViewDto> candidates = candidateService
		    .findAllByTaskAndShift(task, shift);
	    candidateService.rotateCandidates(candidates);
	});
    }

    private void incrementReleaseVersion() {
	releaseService.incrementReleaseVersion();
    }

    private void rotateReleasers() {
	Set<CandidateViewDto> candidates = candidateService
		.findAllByTask(RELEASER);
	candidateService.rotateCandidates(candidates);
    }

    private void rotateTesters() {
	Set<CandidateViewDto> candidates = candidateService
		.findAllByTask(TESTER);
	candidateService.rotateCandidates(candidates);
    }
}
