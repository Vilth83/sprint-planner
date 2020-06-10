package fr.vilth.sprintplanner.external_apis.jira.api;

import fr.vilth.sprintplanner.external_apis.jira.model.Ticket;

// FIXME: Controller created for testing purpose. Should be deleting when
// IssueReconciliation is implemented
@SuppressWarnings("javadoc")
public interface JiraService {

    Ticket getByKey(String key);
}
