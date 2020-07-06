package fr.vilth.sprintplanner.domain.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import fr.vilth.sprintplanner.domain.types.IssueState;
import fr.vilth.sprintplanner.external_apis.github.model.Commit;
import fr.vilth.sprintplanner.external_apis.jira.model.Ticket;

/**
 * Represents a reconciliation between Jira and Github.
 * <p>
 * Takes Jira {@code Ticket} associated with corresponding Github {@code Commit}
 * informations and agglomerate them.
 * <p>
 * Jira {@code Ticket} and Github {@code Commit} a considered corresponding when
 * their key is identical.
 * 
 * @author Thierry VILLEPREUX
 */
@SuppressWarnings("unused") // Required fields for ModelMapper
public class ReconciliatedIssue {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter
	    .ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'",
		    Locale.ENGLISH);

    private String key;

    private IssueState state;

    private LocalDate creationDate;

    private String title;

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

    protected ReconciliatedIssue() {
	// protected no args empty constructor
    }

    /**
     * Returns this instance filled with {@code Commit} informations.
     * 
     * @param commit the commit to extract informations from
     * @return this for chaining
     */
    private ReconciliatedIssue withCommit(Commit commit) {
	this.key = commit.getKey();
	this.title = commit.getMessage();
	String creationDateString = commit.getCommitDate();
	LocalDateTime creation = LocalDateTime.parse(creationDateString,
		FORMATTER);
	this.creationDate = creation.toLocalDate();
	this.url = commit.getHtmlUrl();
	return this;
    }

    /**
     * Returns this instance filled with {@code Ticket} informations.
     * 
     * @param ticket the ticket to extract informations from
     * @return this for chaining
     */
    public ReconciliatedIssue with(Ticket ticket) {
	if (ticket != null) {
	    this.assignee = ticket.getFields().getAssignee();
	    this.reporter = ticket.getFields().getReporter();
	    this.requestor = ticket.getFields().getRequestor();
	    this.priority = ticket.getFields().getPriority();
	    this.fixVersion = ticket.getFields().getFixVersions();
	    this.type = ticket.getFields().getIssueType();
	    this.parentKey = ticket.getFields().getParent();
	    this.jiraState = ticket.getFields().getStatus();
	    this.testProofed = ticket.isTestProofed();
	}
	return this;
    }

    public static ReconciliatedIssue of(Commit commit) {
	return new ReconciliatedIssue().withCommit(commit);
    }
}
