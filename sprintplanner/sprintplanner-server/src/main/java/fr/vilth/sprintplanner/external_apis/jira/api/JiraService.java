package fr.vilth.sprintplanner.external_apis.jira.api;

import fr.vilth.sprintplanner.external_apis.jira.model.Ticket;

@SuppressWarnings("javadoc")
public interface JiraService {

    Ticket getByKey(String key);
}
