package fr.vilth.sprintplanner;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fr.vilth.sprintplanner.api.services.CandidateService;
import fr.vilth.sprintplanner.api.services.ReleaseService;
import fr.vilth.sprintplanner.commons.api.AbstractService;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateViewDto;
import fr.vilth.sprintplanner.domain.types.Shift;

@Service
public class ReleaseJob extends AbstractService {

    private final ReleaseService releaseService;

    private final CandidateService candidateService;

    private static final String RELEASER = "releaser";

    private static final String TECHNICAL = "technical";

    private static final String FUNCTIONAL = "functional";

    public ReleaseJob(ReleaseService releaseService,
	    CandidateService candidateService) {
	super();
	this.releaseService = releaseService;
	this.candidateService = candidateService;
    }

    private void incrementReleaseVersion() {
	releaseService.incrementReleaseVersion();
    }

    private void rotateReleasers() {
	Set<CandidateViewDto> candidates = candidateService
		.findAllByTask(RELEASER);
	candidateService.rotateCandidates(candidates);
    }

    @Scheduled(cron = "${cron.config.handle-release}")
    public void handleRelease() {
	// first, the releaser is changed
	rotateReleasers();
	// then, the release is incremented
	incrementReleaseVersion();
    }

    @Scheduled(cron = "${cron.config.handle-support}")
    public void handleSupport() {
	// the support is changed for each shift and task
	rotateSupport(TECHNICAL);
	rotateSupport(FUNCTIONAL);
    }

    private void rotateSupport(String task) {
	List<Shift> shifts = Arrays
		.asList(new Shift[] { Shift.BGL, Shift.PAR });
	shifts.forEach(shift -> {
	    Set<CandidateViewDto> candidates = candidateService
		    .findAllByTaskAndShift(task, shift);
	    candidateService.rotateCandidates(candidates);
	});
    }
}
