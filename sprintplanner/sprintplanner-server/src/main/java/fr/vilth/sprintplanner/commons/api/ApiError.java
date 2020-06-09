package fr.vilth.sprintplanner.commons.api;

/**
 * Object encapsulating error informations.
 * <p>
 * <ul>
 * <li>field : field in error (in case of {@code InvalidArgumentException})
 * <li>defaultMessage : message given by default by the exception
 * 
 * @author Thierry VILLEPREUX
 */
public class ApiError {

    private String field;

    private String defaultMessage;

    /**
     * Return a new instance of {@code ApiError}
     * 
     * @param field the wrong field concerned by
     *        {@code InvalidArgumentException}
     * @param defaultMessage the default error message returned by the exception
     */
    public ApiError(String field, String defaultMessage) {
	this.field = field;
	this.defaultMessage = defaultMessage;
    }

    @Override
    public String toString() {
	return "{field=" + field + ", defaultMessage=" + defaultMessage + "}";
    }
}
