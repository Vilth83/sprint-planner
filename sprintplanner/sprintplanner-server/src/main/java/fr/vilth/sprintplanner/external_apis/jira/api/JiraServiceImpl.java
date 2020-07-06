package fr.vilth.sprintplanner.external_apis.jira.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fr.vilth.sprintplanner.commons.api.AbstractService;
import fr.vilth.sprintplanner.external_apis.jira.model.Ticket;

/**
 * Service calling Jira Api to retrieve {@code Ticket}
 * 
 * @author Thierry VILLEPREUX
 */
@Service
public class JiraServiceImpl extends AbstractService implements JiraService {

    @Value("${config.external-apis.urls.jira}")
    private String jiraUrl;

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
	String url = jiraUrl + key;
	ResponseEntity<Ticket> response = restTemplate.exchange(url,
		HttpMethod.GET, null, Ticket.class);
	return response.getBody();
    }
}
