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

/**
 * Service handling release {@code Job} execution.
 * <p>
 * Autowires needed beans and gives utility methods to be used by
 * {@link JobTriggers}.
 * 
 * @author Thierry VILLEPREUX
 *
 */
@Service
public class ReleaseJob extends AbstractService {

	private final ReleaseService releaseService;

	private final CandidateService candidateService;

	protected ReleaseJob(ReleaseService releaseService, CandidateService candidateService) {
		super();
		this.releaseService = releaseService;
		this.candidateService = candidateService;
	}

	/**
	 * Retrieve all support {@code Candidate}s for given task, rotate them and
	 * persists the changes.
	 * 
	 * @param task the given task (technical, functional)
	 * 
	 */
	public void rotateSupport(String task) {
		List<Shift> shifts = Arrays.asList(Shift.BGL, Shift.PAR);
		shifts.forEach(shift -> {
			Set<CandidateViewDto> candidates = candidateService.findAllByTaskAndShift(task, shift);
			candidateService.rotateCandidates(candidates);
		});
	}

	/**
	 * Calls {@code ReleaseService} {@code incrementReleaseVersion}.
	 */
	public void incrementReleaseVersion() {
		releaseService.incrementReleaseVersion();
	}

	/**
	 * Retrieve all release {@code Candidate}s, rotate them and persists the
	 * changes.
	 */
	public void rotateReleasers() {
		Set<CandidateViewDto> candidates = candidateService.findAllByTask(Constants.RELEASER);
		candidateService.rotateCandidates(candidates);
	}

	/**
	 * Retrieve all test {@code Candidate}s, rotate them and persists the changes.
	 */
	public void rotateTesters() {
		Set<CandidateViewDto> candidates = candidateService.findAllByTask(Constants.TESTER);
		candidateService.rotateCandidates(candidates);
	}
}
