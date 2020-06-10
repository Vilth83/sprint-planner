package fr.vilth.sprintplanner.external_apis.github.api;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.vilth.sprintplanner.external_apis.github.model.Branch;
import fr.vilth.sprintplanner.external_apis.github.model.Commit;

@RestController
@RequestMapping("/github")
@SuppressWarnings("javadoc")
public class GithubController {

	private final GithubService githubService;

	public GithubController(GithubService githubService) {
		this.githubService = githubService;
	}

	@GetMapping("/branches")
	public List<Branch> findAllBranches() {
		return githubService.findAllBranches();
	}

	@GetMapping("/compare")
	public Set<Commit> compareBranches(@RequestParam String currentBranch, @RequestParam String previousBranch) {
		return githubService.compareBranches(currentBranch, previousBranch);
	}
}