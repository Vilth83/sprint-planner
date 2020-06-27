package fr.vilth.sprintplanner.jobs;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import fr.vilth.sprintplanner.api.services.CandidateService;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateViewDto;
import fr.vilth.sprintplanner.domain.types.Shift;

/**
 * Service handling support {@code Job} execution.
 * <p>
 * Autowires needed beans and gives utility methods to be used by
 * {@link JobTriggers}.
 * 
 * @author Thierry VILLEPREUX
 */
@Service
public class SupportJob {

    private final CandidateService candidateService;

    protected SupportJob(CandidateService candidateService) {
	this.candidateService = candidateService;
    }

    /**
     * Retrieve all support {@code Candidate}s for given task, rotate them and
     * persists the changes.
     * 
     * @param task the given task (technical, functional)
     */
    public void rotateSupport(String task) {
	List<Shift> shifts = Arrays.asList(Shift.BGL, Shift.PAR);
	shifts.forEach(shift -> {
	    Set<CandidateViewDto> candidates = candidateService
		    .findAllByTaskAndShift(task, shift);
	    candidateService.rotateCandidates(candidates);
	});
    }
}
