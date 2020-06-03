package fr.vilth.sprintplanner.github.api;

import java.util.List;
import java.util.Set;

import fr.vilth.sprintplanner.github.model.Branch;
import fr.vilth.sprintplanner.github.model.Commit;

public interface GithubService {

    List<Branch> findAllBranches();

    Set<Commit> getCommitPerBranch(String sha);
    
	Set<Commit> compareBranches(String currentBranch, String previousBranch);
}
