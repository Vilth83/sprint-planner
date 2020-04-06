package fr.vilth.sprintplanner.commons.security;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
	//
    }

    public ResourceNotFoundException(String message) {
	super(message);
    }
}
