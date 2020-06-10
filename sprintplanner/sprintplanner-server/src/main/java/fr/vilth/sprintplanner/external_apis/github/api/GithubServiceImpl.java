package fr.vilth.sprintplanner.external_apis.github.api;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fr.vilth.sprintplanner.commons.api.AbstractService;
import fr.vilth.sprintplanner.external_apis.github.model.Branch;
import fr.vilth.sprintplanner.external_apis.github.model.Commit;

/**
 * Service calling GithubApi to retrieve {@code Commit} and {@code Branch}.
 * 
 * @author Thierry VILLEPREUX
 */
@Service
public class GithubServiceImpl extends AbstractService implements GithubService {

	private final RestTemplate restTemplate;

	/**
	 * Protected constructor to autowire needed bean.
	 * <p>
	 * injects {@code RestTemplate} interface to call external API.
	 * 
	 * @param restTemplate the injected {@code RestTemplate}
	 */
	public GithubServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public List<Branch> findAllBranches() {
		String url = "https://api.github.com/repos/vilth83/sprint-planner/branches";
		ParameterizedTypeReference<List<Branch>> listType = new ParameterizedTypeReference<List<Branch>>() {
			//
		};
		ResponseEntity<List<Branch>> response = restTemplate.exchange(url, HttpMethod.GET, null, listType);
		List<Branch> branches = response.getBody();
		return branches;
	}

	@Override
	public Set<Commit> compareBranches(String currentBranch, String previousBranch) {
		Set<Commit> currentBranchCommits = getCommitPerBranch(currentBranch);
		Set<Commit> previousBranchCommits = getCommitPerBranch(previousBranch);
		return compareBranches(currentBranchCommits, previousBranchCommits);
	}

	private Set<Commit> compareBranches(Set<Commit> currentBranchCommits, Set<Commit> previousBranchCommits) {
		currentBranchCommits.removeIf(previousBranchCommits::contains);
		return currentBranchCommits;
	}

	private Set<Commit> getCommitPerBranch(String sha) {
		String url = "https://api.github.com/repos/vilth83/sprint-planner/commits?sha=" + sha + "&page=1&per_page=100";
		ParameterizedTypeReference<List<Object>> listType = new ParameterizedTypeReference<List<Object>>() {
			//
		};
		ResponseEntity<List<Object>> response = restTemplate.exchange(url, HttpMethod.GET, null, listType);
		List<Object> commits = response.getBody();
		return commits.parallelStream().map(commit -> convert(commit, Commit.class)).filter(this::hasKey)
				.map(this::setCommitKey).collect(Collectors.toSet());
	}

	private Commit setCommitKey(Commit commit) {
		Pattern pattern = Pattern.compile("(?)SPL-\\d+");
		Matcher matcher = pattern.matcher(commit.getMessage());
		commit.setKey(matcher.find() ? matcher.group() : null);
		return commit;
	}

	private boolean hasKey(Commit commit) {
		Pattern pattern = Pattern.compile("(?)SPL-\\d+");
		Matcher matcher = pattern.matcher(commit.getMessage());
		return matcher.find();
	}
}
