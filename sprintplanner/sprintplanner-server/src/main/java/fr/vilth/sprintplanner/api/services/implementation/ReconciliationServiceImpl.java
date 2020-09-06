package fr.vilth.sprintplanner.api.services.implementation;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.vilth.sprintplanner.api.services.ProjectService;
import fr.vilth.sprintplanner.api.services.ReconciliationService;
import fr.vilth.sprintplanner.domain.dtos.ReconciliatedIssue;
import fr.vilth.sprintplanner.domain.dtos.project.ProjectViewDto;
import fr.vilth.sprintplanner.external_apis.github.api.GithubService;
import fr.vilth.sprintplanner.external_apis.github.model.Branch;
import fr.vilth.sprintplanner.external_apis.github.model.Commit;
import fr.vilth.sprintplanner.external_apis.jira.api.JiraService;
import fr.vilth.sprintplanner.external_apis.jira.model.Ticket;

@Service
public class ReconciliationServiceImpl implements ReconciliationService {

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
    protected ReconciliationServiceImpl(GithubService githubService,
	    JiraService jiraService, ProjectService projectService) {
	this.githubService = githubService;
	this.jiraService = jiraService;
	this.projectService = projectService;
    }

    @Override
    public List<ReconciliatedIssue> getIssue(String repository,
	    String currentBranch, String previousBranch) {
	ProjectViewDto project = projectService.getProject();
	Set<Commit> commits = githubService.compareBranches(
		project,
		repository,
		currentBranch,
		previousBranch);
	return commits.parallelStream()
		.map(commit -> ReconciliatedIssue.of(commit)
			.with(getJiraTicket(commit)))
		.collect(Collectors.toList());
    }

    private Ticket getJiraTicket(Commit commit) {
	return jiraService.getByKey(commit.getKey());
    }

    @Override
    public List<Branch> findAllBranches(String repository) {
	ProjectViewDto project = projectService.getProject();
	return githubService.findAllBranches(project, repository);
    }

    @Override
    public List<String> findAllRepositories() {
	ProjectViewDto project = projectService.getProject();
	return githubService.findAllRepositories(project);
    }
}
