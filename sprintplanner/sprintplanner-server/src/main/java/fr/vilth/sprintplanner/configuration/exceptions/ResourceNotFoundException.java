package fr.vilth.sprintplanner.configuration.exceptions;

/**
 * Exception thrown if resource is not found.
 * <p>
 * Used to handle 404 Http error.
 * 
 * @author Thierry VILLEPREUX
 *
 */
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5437163224457589318L;

	/**
	 * Public empty no-arg constructor calling {@link RuntimeException} constructor.
	 */
	public ResourceNotFoundException() {
		super();
	}

	/**
	 * Public constructor calling {@link RuntimeException} constructor with given
	 * message.
	 * 
	 * @param message the message given when throwing the exception
	 */
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
