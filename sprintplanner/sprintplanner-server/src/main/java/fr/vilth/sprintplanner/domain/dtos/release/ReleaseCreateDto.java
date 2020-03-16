package fr.vilth.sprintplanner.domain.dtos.release;

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

    public ReleaseCreateDto(int pi, int sprint, int week, String releaser) {
	this.pi = pi;
	this.sprint = sprint;
	this.week = week;
	this.versionNumber = "v" + pi + "." + sprint + "." + week + ".0";
	this.releaser = releaser;
    }

    @Override
    public String toString() {
	return "{pi=" + pi + ", sprint=" + sprint + ", week=" + week
		+ ",versionNumber=" + versionNumber + ", releaser=" + releaser
		+ "}";
    }
}
