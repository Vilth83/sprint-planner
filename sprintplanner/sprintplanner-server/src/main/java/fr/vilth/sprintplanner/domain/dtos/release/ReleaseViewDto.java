package fr.vilth.sprintplanner.domain.dtos.release;

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

    public int getPi() {
	return pi;
    }

    public int getSprint() {
	return sprint;
    }

    public int getWeek() {
	return week;
    }

    public String getReleaseVersion() {
	return "v" + pi + "." + sprint + "." + week;
    }

    @Override
    public String toString() {
	return "{id=" + id + ", pi=" + pi + ", sprint=" + sprint + ", week="
		+ week + ", releaser=" + releaser + "}";
    }
}
