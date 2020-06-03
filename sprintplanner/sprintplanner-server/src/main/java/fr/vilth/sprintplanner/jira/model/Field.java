package fr.vilth.sprintplanner.jira.model;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Field {

	private Long id;

	private TeamMember reporter;
	private TeamMember assignee;
	private TeamMember requestor;

	private Category issueType;
	private Category priority;
	private Category status;

	private Parent parent;
	private String summary;

	private List<FixVersion> fixVersions;
	@JsonAlias("attachment")
	private List<Attachment> attachments;

	public Long getId() {
		return id;
	}

	public String getReporter() {
		return reporter.displayName;
	}

	public String getAssignee() {
		return assignee.displayName;
	}

	public String getRequestor() {
		return requestor.displayName;
	}

	public String getIssueType() {
		return issueType.name;
	}

	public String getPriority() {
		return priority.name;
	}

	public String getStatus() {
		return status.name;
	}

	public String getParent() {
		return parent.key;
	}

	public String getSummary() {
		return summary;
	}

	public List<String> getFixVersions() {
		return fixVersions.stream().map(fv -> fv.name).collect(Collectors.toList());
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

}

class Category {
	private Long id;

	String name;
}

class TeamMember {
	private Long id;

	String displayName;
}

class Parent {
	private Long id;

	String key;
}

class FixVersion {
	String name;
	private String id;
}
