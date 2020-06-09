package fr.vilth.sprintplanner.jira.api;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fr.vilth.sprintplanner.commons.api.AbstractService;
import fr.vilth.sprintplanner.jira.model.Ticket;

// FIXME: modify visibility of unused methods when IssueReconciliation will be
// implemented
/**
 * Service calling Jira Api to retrieve {@code Ticket}
 * 
 * @author Thierry VILLEPREUX
 */
@Service
public class JiraServiceImpl extends AbstractService implements JiraService {

    private final RestTemplate restTemplate;

    /**
     * Protected constructor to autowire needed bean.
     * <p>
     * injects {@code RestTemplate} interface to call external API.
     * 
     * @param restTemplate the injected {@code RestTemplate}
     */
    public JiraServiceImpl(RestTemplate restTemplate) {
	this.restTemplate = restTemplate;
    }

    @Override
    public Ticket getByKey(String key) {
	String url = "http://localhost:9999/jira/ticket/" + key;
	ResponseEntity<Ticket> response = restTemplate.exchange(url,
		HttpMethod.GET, null, Ticket.class);
	Ticket ticket = response.getBody();
	return ticket;
    }
}
