package fr.vilth.sprintplanner.commons.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

/**
 * Custom implementation of {@code TokenEnhancer}.
 * 
 * @author Thierry VILLEPREUX
 *
 */
public class CustomTokenEnhancer implements TokenEnhancer {

	static final String USER_ID_KEY = "userId";

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> additionalInfo = new HashMap<>();
		CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
		additionalInfo.put(USER_ID_KEY, user.getId());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
		return accessToken;
	}
}
