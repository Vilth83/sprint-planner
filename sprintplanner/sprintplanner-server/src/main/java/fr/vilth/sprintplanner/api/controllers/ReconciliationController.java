package fr.vilth.sprintplanner.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.vilth.sprintplanner.api.services.ReconciliationService;
import fr.vilth.sprintplanner.domain.dtos.ReconciliatedIssue;
import fr.vilth.sprintplanner.external_apis.github.model.Branch;
import fr.vilth.sprintplanner.security.annotations.HasRoleUser;

/**
 * {@code RestController} to handle {@code IssueReconciliation}
 * 
 * @author Thierry VILLEPREUX
 */
@RestController
@RequestMapping("/reconciliations")
public class ReconciliationController {

    private final ReconciliationService reconciliationService;

    /**
     * Protected constructor to autowire needed beans.
     * <p>
     * Injects {@code ReconciliationService}.
     * 
     * @param reconciliationService the injected {@code reconciliationService}
     */
    protected ReconciliationController(
	    ReconciliationService reconciliationService) {
	this.reconciliationService = reconciliationService;
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
    protected List<ReconciliatedIssue> getIssue(
	    @RequestParam(required = false) String repository,
	    @RequestParam String currentBranch,
	    @RequestParam String previousBranch) {
	return reconciliationService.getIssue(repository, currentBranch,
		previousBranch);
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
    protected List<Branch> findAllBranches(
	    @RequestParam(required = false) String repository) {
	return reconciliationService.findAllBranches(repository);
    }

    @GetMapping("/repositories")
    @HasRoleUser
    protected List<String> findAllRepositories() {
	return reconciliationService.findAllRepositories();
    }
}
