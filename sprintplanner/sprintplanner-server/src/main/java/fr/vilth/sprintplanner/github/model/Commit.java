package fr.vilth.sprintplanner.github.model;

import java.util.Objects;

public class Commit {

	private String sha;

	private CommitDetails commit;

	private String html_url;

	private String key;

	public String getMessage() {
		return commit.message;
	}

	public String getCommitDate() {
		return commit.author.date;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public String getHtmlUrl() {
		return html_url;
	}

	@Override
	public int hashCode() {
		return Objects.hash(key);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Commit)) {
			return false;
		}
		Commit other = (Commit) obj;
		return Objects.equals(key, other.key);
	}
}

class CommitDetails {

	String sha;
	String url;
	Author author;
	String message;
}

class Author {
	String name;
	String date;
}
