package fr.vilth.sprintplanner.commons.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
	super();
    }

    public ResourceNotFoundException(String string) {
	super(string);
    }
}
