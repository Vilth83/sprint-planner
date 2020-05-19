package fr.vilth.sprintplanner.github.api;

import java.util.List;
import java.util.Set;

import fr.vilth.sprintplanner.github.model.Branch;
import fr.vilth.sprintplanner.github.model.CommitHolder;

public interface GithubService {

    List<Branch> findAllBranches();

    Set<CommitHolder> getCommitPerBranch(String sha);
    
	Set<CommitHolder> compareBranches(String currentBranch, String previousBranch);
}
