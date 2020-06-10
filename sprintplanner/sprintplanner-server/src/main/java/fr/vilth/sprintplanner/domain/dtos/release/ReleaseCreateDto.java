package fr.vilth.sprintplanner.domain.dtos.release;

/**
 * {@code Dto} representing a {@code ReleaseCreateDto}.
 * 
 * @author Thierry VILLEPREUX
 *
 */
public class ReleaseCreateDto {

	private int pi;

	private int sprint;

	private int week;

	private String versionNumber;

	private String releaser;

	/**
	 * Empty no-arg empty constructor.
	 */
	protected ReleaseCreateDto() {
		//
	}

	/**
	 * Creates a new instance of ReleaseCreateDto.
	 * 
	 * @param pi       a pi number
	 * @param sprint   a sprint number
	 * @param week     a week number
	 * @param releaser the designated {@code Candidate}'s name
	 */
	public ReleaseCreateDto(int pi, int sprint, int week, String releaser) {
		this.pi = pi;
		this.sprint = sprint;
		this.week = week;
		this.versionNumber = "v" + pi + "." + sprint + "." + week + ".0";
		this.releaser = releaser;
	}

	@Override
	public String toString() {
		return "{pi=" + pi + ", sprint=" + sprint + ", week=" + week + ", versionNumber=" + versionNumber
				+ ", releaser=" + releaser + "}";
	}
}
