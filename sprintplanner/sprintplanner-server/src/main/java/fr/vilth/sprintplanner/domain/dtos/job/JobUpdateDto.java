package fr.vilth.sprintplanner.domain.dtos.job;

/**
 * {@code Dto} representing a {@code Job} to update.
 * 
 * @author Thierry VILLEPREUX
 *
 */
public class JobUpdateDto {

	private String title;

	private String task;

	private boolean active;

	/**
	 * Getter for title
	 * 
	 * @return a title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Getter for task
	 * 
	 * @return a task
	 */
	public String getTask() {
		return task;
	}

	/**
	 * Getter for active.
	 * 
	 * @return {@code true} if is active; {@code false} otherwise
	 */
	public boolean isActive() {
		return active;
	}
}
