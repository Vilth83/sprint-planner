package fr.vilth.sprintplanner.external_apis.github.api;

import java.util.List;
import java.util.Set;

import fr.vilth.sprintplanner.external_apis.github.model.Branch;
import fr.vilth.sprintplanner.external_apis.github.model.Commit;

@SuppressWarnings("javadoc")
public interface GithubService {

	List<Branch> findAllBranches();

	Set<Commit> compareBranches(String currentBranch, String previousBranch);
}
