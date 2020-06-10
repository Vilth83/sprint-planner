package fr.vilth.sprintplanner.reconciliation;

/**
 * Enum defining consistency between Jira {@code Ticket} and Github
 * {@code Commit}
 * 
 * @author vilth
 *
 */
public enum IssueState {
	/**
	 * Jira {@code Ticket} and Github {@code Commit} are both OPENED
	 */
	OPENED,
	/**
	 * Jira {@code Ticket} and Github {@code Commit}'s pull request are both CLOSED
	 */
	CLOSED,
	/**
	 * Jira {@code Ticket} and Github {@code Commit} statuses are different (closed
	 * Jira and Opened
	 */
	NOT_MATCHING

}
