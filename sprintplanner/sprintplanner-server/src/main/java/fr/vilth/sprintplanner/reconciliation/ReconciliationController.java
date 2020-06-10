package fr.vilth.sprintplanner.reconciliation;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.vilth.sprintplanner.external_apis.github.api.GithubController;
import fr.vilth.sprintplanner.external_apis.github.model.Commit;
import fr.vilth.sprintplanner.external_apis.jira.api.JiraController;
import fr.vilth.sprintplanner.external_apis.jira.model.Ticket;

@RestController
@RequestMapping("/reconciliation")
public class ReconciliationController {

	@Autowired
	GithubController githubController;
	@Autowired
	JiraController jiraController;

	@GetMapping("/test")
	public List<ReconciliatedIssue> getIssue(String currentBranch, String previousBranch) {
		Set<Commit> commits = githubController.compareBranches(currentBranch, previousBranch);

		return commits.parallelStream().map(commit -> {
			Ticket ticket = jiraController.getByKey(commit.getKey());
			return new ReconciliatedIssue().withTicket(ticket).withCommit(commit);
		}).collect(Collectors.toList());
	}
}
