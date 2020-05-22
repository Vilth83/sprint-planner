package fr.vilth.sprintplanner.jira.model;

public class Attachment {
	private Long id;

	private String filename;
	private String content;

	public Long getId() {
		return id;
	}

	public String getFilename() {
		return filename;
	}

	public String getContent() {
		return content;
	}

}