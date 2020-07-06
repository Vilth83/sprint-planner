package fr.vilth.sprintplanner.api.controllers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.vilth.sprintplanner.api.services.ProjectService;
import fr.vilth.sprintplanner.domain.dtos.ReconciliatedIssue;
import fr.vilth.sprintplanner.domain.dtos.project.ProjectViewDto;
import fr.vilth.sprintplanner.external_apis.github.api.GithubService;
import fr.vilth.sprintplanner.external_apis.github.model.Branch;
import fr.vilth.sprintplanner.external_apis.github.model.Commit;
import fr.vilth.sprintplanner.external_apis.jira.api.JiraService;
import fr.vilth.sprintplanner.external_apis.jira.model.Ticket;
import fr.vilth.sprintplanner.security.annotations.HasRoleUser;

/**
 * {@code RestController} to handle {@code IssueReconciliation}
 * 
 * @author Thierry VILLEPREUX
 */
@RestController
@RequestMapping("/reconciliations")
public class ReconciliationController {

    private final GithubService githubService;

    private final JiraService jiraService;

    private final ProjectService projectService;

    /**
     * Protected constructor to autowire needed beans.
     * <p>
     * Injects {@code GithubService}, {@code JiraService} and
     * {@code ProjectService}.
     * 
     * @param githubService the injected {@code GithubService}
     * @param jiraService the injected {@code JiraService}
     * @param projectService the injected {@code ProjectService}
     */
    protected ReconciliationController(GithubService githubService,
	    JiraService jiraService, ProjectService projectService) {
	this.githubService = githubService;
	this.jiraService = jiraService;
	this.projectService = projectService;
    }

    /**
     * Returns a {@code List} of {@code ReconciliatedIssue}.
     * <p>
     * <ul>
     * <li>compares two Github {@code Branches} to retrieve a {@code Set} of
     * {@code commit}
     * <li>retrieve Jira {@code Ticket} for each {@code Commit} of the
     * {@code Set}
     * <li>build a {@code ReconciliatedIssue} with {@code Commit} and
     * {@code Ticket}
     * <li>returns the created {@code List} of {@code ReconciliatedIssue}
     * </ul>
     * 
     * @param repository optional. If given issues are compared for given
     *        repository
     * @param currentBranch the branch candidate to release
     * @param previousBranch the previous release's branch
     * @return a {@code List} of {@code reconciliatedIssue}
     */
    @GetMapping("/reconciliate")
    @HasRoleUser
    public List<ReconciliatedIssue> getIssue(
	    @RequestParam(required = false) String repository,
	    @RequestParam String currentBranch,
	    @RequestParam String previousBranch) {
	ProjectViewDto project = projectService.getProject();
	Set<Commit> commits = githubService.compareBranches(project, repository,
		currentBranch,
		previousBranch);
	return commits.parallelStream()
		.map(commit -> ReconciliatedIssue.ofCommit(commit)
			.withTicket(getTicket(commit)))
		.collect(Collectors.toList());
    }

    private Ticket getTicket(Commit commit) {
	return jiraService.getByKey(commit.getKey());
    }

    /**
     * Retrieve all branches for given repository from GitHub API.
     * 
     * @param repository optional. If given, branches are retrieved from given
     *        repository instead of default one.
     * @return a {@code List} of {@code Branch}
     */
    @GetMapping("/branches")
    @HasRoleUser
    public List<Branch> findAllBranches(
	    @RequestParam(required = false) String repository) {
	ProjectViewDto project = projectService.getProject();
	return githubService.findAllBranches(project, repository);
    }
}
