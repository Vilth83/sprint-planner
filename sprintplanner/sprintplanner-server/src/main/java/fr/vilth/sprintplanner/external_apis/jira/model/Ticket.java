package fr.vilth.sprintplanner.external_apis.jira.model;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Represents a Jira {@code Ticket}.
 * <p>
 * This class follows Jira API format.
 * 
 * @author Thierry VILLEPREUX
 *
 */
public class Ticket {

	/*
	 * accepts case insensitive strings formatted as follow : "test proof",
	 * "test-proof" or "test_proof"
	 */
	private static final String REGEX = "(?i)(test)+[-_ ]+(proof)";

	private String id;
	private String key;
	private Field fields;

	/**
	 * Getter for id.
	 * 
	 * @return an identifier
	 */
	public String getId() {
		return id;
	}

	/**
	 * Getter for key.
	 * 
	 * @return a key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Getter for {@code Field}
	 * 
	 * @return return a {@code Field} object encapsulating fields.
	 */
	public Field getFields() {
		return fields;
	}

	/**
	 * Getter for testProofed.
	 * <p>
	 * A {@code Ticket} is considered as test proofed if and only if it contains at
	 * least one attachement having "test proof" in its name. the regular expression
	 * for "test proof" mention in case insensitive and accepts space, dash and
	 * underscore between the two words.
	 * 
	 * @return {@code true} if is test proofed; {@code false} otherwise
	 */
	public boolean isTestProofed() {
		List<Attachment> attachments = this.fields.getAttachments();
		Pattern pattern = Pattern.compile(REGEX);
		return attachments.stream().anyMatch(attachment -> pattern.matcher(attachment.getFilename()).find());
	}

}
