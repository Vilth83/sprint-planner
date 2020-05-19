package fr.vilth.sprintplanner.github.api;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.vilth.sprintplanner.github.model.Branch;
import fr.vilth.sprintplanner.github.model.CommitHolder;

@RestController
@RequestMapping("/github")
public class GithubController {

	private final GithubService githubService;

	public GithubController(GithubService githubService) {
		this.githubService = githubService;
	}

	@GetMapping("/branches")
	public List<Branch> findAllBranches() {
		return githubService.findAllBranches();
	}

	@GetMapping("/filter")
	public Set<CommitHolder> getCommitPerBranch(@RequestParam String sha) {
		return githubService.getCommitPerBranch(sha);
	}

	@GetMapping("/compare")
	public Set<CommitHolder> compareBranches(@RequestParam String currentBranch, @RequestParam String previousBranch) {
		return githubService.compareBranches(currentBranch, previousBranch);
	}
}