package fr.vilth.sprintplanner.jobs;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import fr.vilth.sprintplanner.api.services.CandidateService;
import fr.vilth.sprintplanner.api.services.ReleaseService;
import fr.vilth.sprintplanner.commons.api.AbstractService;
import fr.vilth.sprintplanner.commons.utils.Constants;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateViewDto;
import fr.vilth.sprintplanner.domain.types.Shift;

@Service
public class ReleaseJob extends AbstractService {

    private final ReleaseService releaseService;

    private final CandidateService candidateService;

    public ReleaseJob(ReleaseService releaseService,
	    CandidateService candidateService) {
	super();
	this.releaseService = releaseService;
	this.candidateService = candidateService;
    }

    public void rotateSupport(String task) {
	List<Shift> shifts = Arrays.asList(Shift.BGL, Shift.PAR);
	shifts.forEach(shift -> {
	    Set<CandidateViewDto> candidates = candidateService
		    .findAllByTaskAndShift(task, shift);
	    candidateService.rotateCandidates(candidates);
	});
    }

    public void incrementReleaseVersion() {
	releaseService.incrementReleaseVersion();
    }

    public void rotateReleasers() {
	Set<CandidateViewDto> candidates = candidateService
		.findAllByTask(Constants.RELEASER);
	candidateService.rotateCandidates(candidates);
    }

    public void rotateTesters() {
	Set<CandidateViewDto> candidates = candidateService
		.findAllByTask(Constants.TESTER);
	candidateService.rotateCandidates(candidates);
    }
}
