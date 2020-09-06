package fr.vilth.sprintplanner.domain.types;

/**
 * Enum representing current status of a {@code Candidate}.
 * <p>
 * {@code Status}es concern a
 * {@linkplain fr.vilth.sprintplanner.domain.entities.Candidate Candidate}, for
 * a given {@linkplain fr.vilth.sprintplanner.domain.entities.Task Task}
 * <p>
 * 
 * @author Thierry VILLEPREUX
 */
public enum Status {
    /**
     * Is eligible to be selected for a {@code Task}.
     */
    AVAILABLE,
    /**
     * Is not available, therefore cannot be selected for any {@code Task}.
     */
    UNAVAILABLE,
    /**
     * Is current selected {@code Candidate} for given {@code Task}.
     */
    CURRENT
}
