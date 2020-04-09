package fr.vilth.sprintplanner.domain.dtos.job;

public class JobUpdateDto {

    private String title;

    private String task;

    private boolean active;

    public String getTitle() {
	return title;
    }

    public String getTask() {
	return task;
    }

    public boolean isActive() {
	return active;
    }
}
