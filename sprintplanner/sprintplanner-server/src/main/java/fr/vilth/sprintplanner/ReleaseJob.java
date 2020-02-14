package fr.vilth.sprintplanner;

import java.util.Set;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fr.vilth.sprintplanner.api.services.CandidateService;
import fr.vilth.sprintplanner.api.services.ReleaseService;
import fr.vilth.sprintplanner.commons.api.AbstractService;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateViewDto;

@Service
public class ReleaseJob extends AbstractService {

    private final ReleaseService releaseService;

    private final CandidateService candidateService;

    private static final String RELEASER = "releaser";

    public ReleaseJob(ReleaseService releaseService,
	    CandidateService candidateService) {
	super();
	this.releaseService = releaseService;
	this.candidateService = candidateService;
    }

    @Scheduled(cron = "${cron.config.increment-version}")
    public void incrementReleaseVersion() {
	releaseService.incrementReleaseVersion();
    }

    @Scheduled(cron = "${cron.config.rotate-releasers}")
    public void rotateReleasers() {
	Set<CandidateViewDto> candidates = candidateService
		.findAllByTask(RELEASER);
	candidateService.rotateCandidates(candidates);
    }
}
