package fr.vilth.sprintplanner.external_apis.jira.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.vilth.sprintplanner.external_apis.jira.model.Ticket;

// FIXME: Controller created for testing purpose. Should be deleting when
// IssueReconciliation is implemented
@SuppressWarnings("javadoc")
@RestController
@RequestMapping("/jira")
public class JiraController {

    private final JiraService jiraService;

    public JiraController(JiraService jiraService) {
	this.jiraService = jiraService;
    }

    @GetMapping("/ticket/{key}")
    public Ticket getByKey(@PathVariable String key) {
	return jiraService.getByKey(key);
    }
}
