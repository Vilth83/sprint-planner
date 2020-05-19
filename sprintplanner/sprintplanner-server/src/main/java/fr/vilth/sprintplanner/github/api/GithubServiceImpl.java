package fr.vilth.sprintplanner.github.api;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fr.vilth.sprintplanner.github.model.Branch;
import fr.vilth.sprintplanner.github.model.CommitHolder;

@Service
public class GithubServiceImpl implements GithubService {

	private final RestTemplate restTemplate;

	private final ModelMapper mapper;

	public GithubServiceImpl(RestTemplate restTemplate, ModelMapper mapper) {
		this.restTemplate = restTemplate;
		this.mapper = mapper;
	}

	@Override
	public List<Branch> findAllBranches() {
		String url = "https://api.github.com/repos/vilth83/sprint-planner/branches";
		ParameterizedTypeReference<List<Branch>> listType = new ParameterizedTypeReference<List<Branch>>() {
		};
		ResponseEntity<List<Branch>> response = restTemplate.exchange(url, HttpMethod.GET, null, listType);
		List<Branch> branches = response.getBody();
		return branches;
	}

	@Override
	public Set<CommitHolder> getCommitPerBranch(String sha) {
		String url = "https://api.github.com/repos/vilth83/sprint-planner/commits?sha=" + sha + "&page=1&per_page=100";
		ParameterizedTypeReference<List<Object>> listType = new ParameterizedTypeReference<List<Object>>() {
			//
		};
		ResponseEntity<List<Object>> response = restTemplate.exchange(url, HttpMethod.GET, null, listType);
		List<Object> commits = response.getBody();
		return commits.parallelStream().map(commit -> mapper.map(commit, CommitHolder.class)).filter(this::hasKey)
				.map(this::setCommitKey).collect(Collectors.toSet());
	}

	private CommitHolder setCommitKey(CommitHolder commit) {
		Pattern pattern = Pattern.compile("(?)SPL-\\d+");
		Matcher matcher = pattern.matcher(commit.getMessage());
		commit.setKey(matcher.find() ? matcher.group() : null);
		return commit;
	}

	private boolean hasKey(CommitHolder commit) {
		Pattern pattern = Pattern.compile("(?)SPL-\\d+");
		Matcher matcher = pattern.matcher(commit.getMessage());
		return matcher.find();
	}

	@Override
	public Set<CommitHolder> compareBranches(String currentBranch, String previousBranch) {
		Set<CommitHolder> currentBranchCommits = getCommitPerBranch(currentBranch);
		Set<CommitHolder> previousBranchCommits = getCommitPerBranch(previousBranch);

		return compareBranches(currentBranchCommits, previousBranchCommits);
	}

	private Set<CommitHolder> compareBranches(Set<CommitHolder> currentBranchCommits,
			Set<CommitHolder> previousBranchCommits) {
		currentBranchCommits.removeIf(previousBranchCommits::contains);
		return currentBranchCommits;
	}
}
