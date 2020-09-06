package fr.vilth.sprintplanner.domain.dtos;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import fr.vilth.sprintplanner.SetupUnitTest;
import fr.vilth.sprintplanner.external_apis.github.model.Commit;
import fr.vilth.sprintplanner.external_apis.jira.model.Ticket;

public class ReconciliatedIssueTest extends SetupUnitTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/commitCreation.csv", delimiter = ';')
    void should_create_reconciliatedIssue(String json) {
	Commit commit = jsonConvert(json, Commit.class);
	ReconciliatedIssue tested = ReconciliatedIssue.of(commit);
	assertNotNull(tested);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/commitCreation.csv", delimiter = ';')
    void should_create_reconciliatedIssue_with_jira(String json) {
	Commit commit = jsonConvert(json, Commit.class);
	String ticketJson = "{\"id\":\"1\",\"fields\":{\"id\":\"1\",\"assignee\":{\"displayName\":\"Thierry VILLEPREUX\"},"
		+ "\"attachment\":[{\"content\":\"string\",\"filename\":\"[SPL-00]Testproof\"}],\"fixVersions\":[{\"name\":\"v1.1.0.0\"}],"
		+ "\"issueType\":{\"name\":\"Story\"},\"priority\":{\"name\":\"high\"},\"reporter\":{\"displayName\":\"FrankMarshall\"},"
		+ "\"status\":{\"name\":\"CLOSED\"},\"summary\":\"[SPL-00]Addjiraticketretrieval\"},\"key\":\"SPL-00\"}";
	Ticket ticket = jsonConvert(ticketJson, Ticket.class);
	ReconciliatedIssue tested = ReconciliatedIssue.of(commit).with(ticket);
	assertNotNull(tested);
    }
}
