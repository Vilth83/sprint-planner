package fr.vilth.sprintplanner.reconciliation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import fr.vilth.sprintplanner.github.model.Commit;
import fr.vilth.sprintplanner.jira.model.Ticket;

public class ReconciliatedIssue {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'",
			Locale.ENGLISH);
	private String key; // ticket number
	private State state; // Closed / Opened / not matching
	private LocalDate creationDate; // PR creation date
	private String title; // Github name
	private List<String> fixVersion;
	private String priority;
	private String requestor;
	private String type;
	private String reporter;
	private String assignee;
	private String parentKey;
	private String jiraState;
	private boolean testProofed;
	private String url;

	public ReconciliatedIssue() {

	}

	public ReconciliatedIssue withCommit(Commit commit) {
		this.key = commit.getKey();
		this.title = commit.getMessage().substring(0, commit.getMessage().indexOf("\n"));
		String creationDateString = commit.getCommitDate();
		LocalDateTime creation = LocalDateTime.parse(creationDateString, FORMATTER);
		this.creationDate = creation.toLocalDate();
		this.url = commit.getHtmlUrl();
		return this;
	}

	public ReconciliatedIssue withTicket(Ticket ticket) {
		this.assignee = ticket.getFields().getAssignee();
		this.reporter = ticket.getFields().getReporter();
		this.requestor = ticket.getFields().getRequestor();
		this.priority = ticket.getFields().getPriority();
		this.fixVersion = ticket.getFields().getFixVersions();
		this.type = ticket.getFields().getIssueType();
		this.parentKey = ticket.getFields().getParent();
		this.jiraState = ticket.getFields().getStatus();
		this.testProofed = ticket.isTestProofed();

		return this;
	}

}
