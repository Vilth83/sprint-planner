package fr.vilth.sprintplanner.domain.dtos.release;

/**
 * {@code Dto} representating informations of a {@code Release}.
 * 
 * @author Thierry VILLEPREUX
 *
 */
public class ReleaseViewDto {

	private Long id;

	private int pi;

	private int sprint;

	private int week;

	private String releaser;

	/**
	 * Empty no-arg empty constructor.
	 */
	protected ReleaseViewDto() {
		//
	}

	/**
	 * Getter for pi
	 * 
	 * @return a pi
	 */
	public int getPi() {
		return pi;
	}

	/**
	 * Getter for sprint
	 * 
	 * @return a sprint
	 */
	public int getSprint() {
		return sprint;
	}

	/**
	 * Getter for week
	 * 
	 * @return a week
	 */
	public int getWeek() {
		return week;
	}

	/**
	 * Constructs the release version number as a {@code String}.
	 * <p>
	 * format of a release version number is v.{pi}.{sprint}.{week}
	 * 
	 * @return stringified release number
	 */
	public String getReleaseVersion() {
		return "v" + pi + "." + sprint + "." + week;
	}

	@Override
	public String toString() {
		return "{id=" + id + ", pi=" + pi + ", sprint=" + sprint + ", week=" + week + ", releaser=" + releaser + "}";
	}
}
