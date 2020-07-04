package fr.vilth.sprintplanner.external_apis.jira.model;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonAlias;

/**
 * Class representing a Jira {@code Ticket} field.
 * <p>
 * JIRA API returns {@code Field} as an object, encapsulating different fields.
 * We need to mirror this behavior to retrieve {@code Ticket}s.
 * 
 * @author Thierry VILLEPREUX
 */
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

    /**
     * id getter
     * 
     * @return id
     */
    public Long getId() {
	return id;
    }

    /**
     * getter returning this {@code Field} {@code Reporter}'s display name
     * 
     * @return the name of the {@code Reporter}
     */
    public String getReporter() {
	return reporter.displayName;
    }

    /**
     * getter returning this {@code Field} {@code Assignee}'s display name
     * 
     * @return the name of the {@code Assignee}
     */
    public String getAssignee() {
	return getOrNull(assignee);
    }

    private String getOrNull(TeamMember tested) {
	return tested != null ? tested.displayName : null;
    }

    /**
     * getter returning this {@code Field} {@code Requestor}'s display name
     * 
     * @return the name of the {@code Requestor}
     */
    public String getRequestor() {
	return getOrNull(requestor);
    }

    /**
     * getter returning this {@code Field} {@code IssueType}'s name
     * 
     * @return the name of the {@code IssueType}
     */
    public String getIssueType() {
	return issueType.name;
    }

    /**
     * getter returning this {@code Field} {@code Priority}'s name
     * 
     * @return the name of the {@code Priority}
     */
    public String getPriority() {
	return priority.name;
    }

    /**
     * getter returning this {@code Field} {@code Status}'s name
     * 
     * @return the name of the {@code Status}
     */
    public String getStatus() {
	return status.name;
    }

    /**
     * getter returning this {@code Field} {@code Parent}'s key
     * 
     * @return the key of the {@code Parent}
     */
    public String getParent() {
	return parent != null ? parent.key : null;
    }

    /**
     * getter returning this {@code Field} summary
     * 
     * @return the summary
     */
    public String getSummary() {
	return summary;
    }

    /**
     * Getter for the {@code List} of {@code FixVersion}
     * <p>
     * get the {@code name} of each {@code FixVersion} and returns them as a
     * {@code List}
     * 
     * @return a {@code List} of {@code FixVersion} name
     */
    public List<String> getFixVersions() {
	return fixVersions.stream().map(fv -> fv.name)
		.collect(Collectors.toList());
    }

    /**
     * Getter for {@code Attachement}
     * 
     * @return the {@code List} of {@code Attachement}s
     */
    public List<Attachment> getAttachments() {
	return attachments;
    }
}

class Category {

    @SuppressWarnings("unused") // required for Jakson mapping
    private Long id;

    String name;
}

class TeamMember {

    @SuppressWarnings("unused") // required for Jakson mapping
    private Long id;

    String displayName;
}

class Parent {

    @SuppressWarnings("unused") // required for Jakson mapping
    private Long id;

    String key;
}

class FixVersion {

    @SuppressWarnings("unused") // required for Jakson mapping
    private String id;

    String name;
}
