package fr.vilth.sprintplanner.api.services;

import java.util.List;

import fr.vilth.sprintplanner.domain.dtos.ReconciliatedIssue;
import fr.vilth.sprintplanner.external_apis.github.model.Branch;

public interface ReconciliationService {

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
    List<ReconciliatedIssue> getIssue(String repository, String currentBranch,
	    String previousBranch);

    /**
     * Retrieve all branches for given repository from GitHub API.
     * 
     * @param repository optional. If given, branches are retrieved from given
     *        repository instead of default one.
     * @return a {@code List} of {@code Branch}
     */
    List<Branch> findAllBranches(String repository);

    public List<String> findAllRepositories();
}
