package fr.vilth.sprintplanner.security;

import java.util.Map;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.stereotype.Component;

/**
 * 
 * Custom implementation of {@link AccessTokenConverter}.
 * <p>
 * Provides custom configuration for
 * {@link AccessTokenConverter#extractAuthentication(Map) extractAuthentication
 * }
 * 
 * @author Thierry VILLEPREUX
 *
 */
@Component
public class CustomAccessTokenConverter extends DefaultAccessTokenConverter {

	@Override
	public OAuth2Authentication extractAuthentication(Map<String, ?> claims) {
		OAuth2Authentication authentication = super.extractAuthentication(claims);
		authentication.setDetails(claims); // Prevents details set to null
		return authentication;
	}
}
