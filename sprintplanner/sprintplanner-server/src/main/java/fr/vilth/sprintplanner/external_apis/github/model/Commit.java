package fr.vilth.sprintplanner.external_apis.github.model;

import java.util.Objects;

/**
 * Represents a github commit.
 * 
 * @author Thierry VILLEPREUX
 *
 */
public class Commit {

	@SuppressWarnings("unused") // required for Jackson mapping
	private String sha;

	private CommitDetails commit;

	private String html_url; // does not respect Javabean conventions because of Github json format

	private String key;

	@SuppressWarnings("unused") // required for Jackson mapping
	private String url;

	/**
	 * Getter for message
	 * <p>
	 * Returns this {@code CommitDetails} message
	 * 
	 * @return a Commit message
	 */
	public String getMessage() {
		return commit.message;
	}

	/**
	 * Getter for commit date.
	 * <p>
	 * Returns this {@code CommitDetails} {@code Author} date
	 * 
	 * @return the date of this commit as a {@code String}
	 */
	public String getCommitDate() {
		return commit.author.date;
	}

	/**
	 * Setter for key
	 * 
	 * @param key the key to be set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Getter for key
	 * 
	 * @return this key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Getter for HtmlUrl
	 * 
	 * @return url of the {@code Commit}
	 */
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
