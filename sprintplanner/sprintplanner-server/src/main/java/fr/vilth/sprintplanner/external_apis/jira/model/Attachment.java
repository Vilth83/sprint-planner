package fr.vilth.sprintplanner.external_apis.jira.model;

/**
 * Attachment of a Jira {@code Ticket}
 * 
 * @author Thierry VILLEPREUX
 */
public class Attachment {

    @SuppressWarnings("unused") // Mandatory for jackson mapping
    private Long id;

    private String filename;

    @SuppressWarnings("unused") // Mandatory for jackson mapping
    private String content;

    /**
     * Returns the file name of this {@code Attachement}
     * 
     * @return a {@code String} corresponding to the file name
     */
    public String getFilename() {
	return filename;
    }
}