package fr.vilth.sprintplanner.domain.utils;

import java.util.Set;

import fr.vilth.sprintplanner.commons.utils.PriorityComparator;
import fr.vilth.sprintplanner.domain.entities.Candidate;
import fr.vilth.sprintplanner.domain.types.Status;

/**
 * Utility class providing round robin algorithm.
 * 
 * @author Thierry VILLEPREUX
 */
public final class RoundRobinHandler {

    private RoundRobinHandler() {
	// private constructor to ensure non-instanciability
    }

    /**
     * Method that rotates {@code Candidate} using Round robin logic.
     * 
     * @param candidates
     */
    public static void rotate(Set<Candidate> candidates) {
	candidates.forEach(candidate -> {
	    // increment priority of each candidate
	    candidate.incrementPriority();
	    if (candidate.getStatus().equals(Status.CURRENT)) {
		/*
		 * given candidate status is set to "AVAILABLE" and its priority
		 * is set to 0 to put him at the tail of the queue.
		 */
		candidate.becomesPrevious();
	    } else if (candidate.getStatus().equals(Status.UNAVAILABLE)) {
		/**
		 * Unavailable candidates are reset to available each week
		 */
		candidate.setStatus(Status.AVAILABLE);
	    }
	});
	/*
	 * search for the first available candidate with the highest priority
	 * and set his status to current
	 */
	candidates.stream().filter(Candidate::isAvailable)
		.max(new PriorityComparator())
		.ifPresent(Candidate::becomesCurrent);
    }
}
