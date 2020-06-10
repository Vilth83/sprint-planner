package fr.vilth.sprintplanner.external_apis.jira.model;

import java.util.List;
import java.util.regex.Pattern;

public class Ticket {

	private String id;
	private String key;
	private Field fields;

	public String getId() {
		return id;
	}

	public String getKey() {
		return key;
	}

	public Field getFields() {
		return fields;
	}

	public boolean isTestProofed() {
		List<Attachment> attachments = this.fields.getAttachments();
		Pattern pattern = Pattern.compile("(?i)(test)+[-_ ]+(proof)");
		return attachments.stream().anyMatch(attachment -> pattern.matcher(attachment.getFilename()).find());
	}

}
