package fr.vilth.sprintplanner.external_apis.github.api;

import java.util.List;
import java.util.Set;

import fr.vilth.sprintplanner.domain.dtos.project.ProjectViewDto;
import fr.vilth.sprintplanner.external_apis.github.model.Branch;
import fr.vilth.sprintplanner.external_apis.github.model.Commit;

@SuppressWarnings("javadoc")
public interface GithubService {

    public List<String> findAllRepositories(ProjectViewDto project);

    List<Branch> findAllBranches(ProjectViewDto project, String repository);

    Set<Commit> compareBranches(ProjectViewDto project, String repository,
	    String currentBranch,
	    String previousBranch);
}
