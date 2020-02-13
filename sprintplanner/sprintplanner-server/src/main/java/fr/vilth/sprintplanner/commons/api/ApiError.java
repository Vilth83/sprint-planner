package fr.vilth.sprintplanner.commons.api;

public class ApiError {

    private String field;

    private String defaultMessage;

    public ApiError(String field, String defaultMessage) {
	this.field = field;
	this.defaultMessage = defaultMessage;
    }
}
