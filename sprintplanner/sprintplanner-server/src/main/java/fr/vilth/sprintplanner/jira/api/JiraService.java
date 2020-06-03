package fr.vilth.sprintplanner.jira.api;

import fr.vilth.sprintplanner.jira.model.Ticket;

public interface JiraService {

	Ticket getByKey(String key);

}
