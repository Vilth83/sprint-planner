package fr.vilth.sprintplanner.external_apis.github.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.vilth.sprintplanner.external_apis.github.model.Branch;

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
}