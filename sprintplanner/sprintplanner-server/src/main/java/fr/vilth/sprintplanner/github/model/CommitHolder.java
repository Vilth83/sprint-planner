package fr.vilth.sprintplanner.github.model;

import java.util.Objects;

public class CommitHolder {

    private String sha;

    private CodeCommit commit;

    private String url;

    private String key;

    public String getMessage() {
	return this.commit.message;
    }

    public void setKey(String key) {
	this.key = key;
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
	if (!(obj instanceof CommitHolder)) {
	    return false;
	}
	CommitHolder other = (CommitHolder) obj;
	return Objects.equals(key, other.key);
    }
}

class CodeCommit {

    String message;
}
