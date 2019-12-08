package fr.vilth.sprintplanner.domain.types;

/**
 * Enum representing current status of a {@code Candidate}.
 * <p>
 * {@code Status}es concern a
 * {@linkplain fr.vilth.sprintplanner.domain.entities.Candidate Candidate}, for
 * a given {@linkplain fr.vilth.sprintplanner.dmain.entities.Task Task}
 * <p>
 * Existing statuses are :
 * <ul>
 * <li>AVAILABLE : is available to be selected for a {@code Task}
 * <li>UNAVAILABLE : is unavailable, therefore cannot be selected for a
 * {@code Task}
 * <li>CURRENT : is current selected {@code Candidate} for a {@code Task}
 * <li>NEXT : is next selected {@code Candidate} for a {@code Task}
 * </ul>
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
    CURRENT,
    /**
     * Is next selected {@code Candidate} for given {@code Task}.
     */
    NEXT
}
