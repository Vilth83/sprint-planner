package fr.vilth.sprintplanner.commons.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.vilth.sprintplanner.api.services.CustomUserDetailsService;
import fr.vilth.sprintplanner.domain.dtos.custom_user.CustomUserInfoDto;

/**
 * Configuration of the {@code AuthorizationServer}.
 * <p>
 * Configures Oauth2 with JWT token store, token enhancer and provides standard
 * auth endpoint.
 * 
 * @author Thierry VILLEPREUX
 */
@Configuration
@EnableAuthorizationServer
@RestController // for "/me" endpoint
public class AuthorizationServerConfig
	extends AuthorizationServerConfigurerAdapter {

    @Value("${jwt-auth-server.keyStore}")
    private String keyStore;

    @Value("${jwt-auth-server.keyPass}")
    private String keyPass;

    @Value("${jwt-auth-server.keyAlias}")
    private String keyAlias;

    @Value("${jwt-auth-server.accessTokenValiditySeconds}")
    private int accessTokenValiditySeconds;

    @Value("${jwt-auth-server.refreshTokenValiditySeconds}")
    private int refreshTokenValiditySeconds;

    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailsService userDetailsService;

    private final CustomAccessTokenConverter customAccessTokenConverter;

    private final PasswordEncoder passwordEncoder;

    protected AuthorizationServerConfig(
	    AuthenticationManager authenticationManagerBean,
	    CustomUserDetailsService userDetailsService,
	    CustomAccessTokenConverter customAccessTokenConverter,
	    PasswordEncoder passwordEncoder) {
	authenticationManager = authenticationManagerBean;
	this.userDetailsService = userDetailsService;
	this.customAccessTokenConverter = customAccessTokenConverter;
	this.passwordEncoder = passwordEncoder;
    }

    /**
     * Token service using random UUID values for the access token and refresh
     * token values.
     * <p>
     * Specifies the token store and enables the refresh token.
     * 
     * @return a {@code TokenService}
     */
    @Bean
    protected DefaultTokenServices tokenServices() {
	DefaultTokenServices services = new DefaultTokenServices();
	services.setTokenStore(tokenStore());
	services.setSupportRefreshToken(true);
	return services;
    }

    /**
     * returns a JwtTokenStore to read and write JWT thanks to the token
     * converter.
     * 
     * @return a {@code JwtTokenStore}
     */
    @Bean
    protected TokenStore tokenStore() {
	return new JwtTokenStore(accessTokenConverter());
    }

    /**
     * Bean that returns a
     * {@linkplain fr.vilth.sprintplanner.commons.security.CustomTokenEnhancer
     * custom token enhancer}
     * 
     * @return a custom token enhancer
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
	return new CustomTokenEnhancer();
    }

    /**
     * All in one configuration.
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer configurer)
	    throws Exception {
	TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
	tokenEnhancerChain.setTokenEnhancers(
		Arrays.asList(tokenEnhancer(), accessTokenConverter()));
	configurer.tokenStore(tokenStore()).tokenEnhancer(tokenEnhancerChain)
		.authenticationManager(authenticationManager)
		.userDetailsService(userDetailsService);
    }

    /**
     * A token converter for JWT and specifies a signing key (private/public key
     * pair).
     * 
     * @return an access token converter
     */
    @Bean
    protected JwtAccessTokenConverter accessTokenConverter() {
	JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	Resource resource = new ClassPathResource(keyStore);
	char[] password = keyPass.toCharArray();
	KeyStoreKeyFactory factory = new KeyStoreKeyFactory(resource, password);
	converter.setKeyPair(factory.getKeyPair(keyAlias));
	converter.setAccessTokenConverter(customAccessTokenConverter);
	return converter;
    }

    /**
     * Change authorization server security allowing form auth for clients (vs
     * HTTP Basic). The client_id is sent as form parameter instead.
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer configurer)
	    throws Exception {
	configurer.allowFormAuthenticationForClients();
    }

    /**
     * In memory client with empty secret, application is a "private" API with a
     * single client, but Spring forces a client authentication.
     * <p>
     * Authorized grant types are <i>password</i> and <i>refresh_token</i>.
     * <p>
     * The scope is trusted (convention) and no need to specify it during client
     * authentication. We do not use scope-based authorization in this
     * application.
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients)
	    throws Exception {
	clients.inMemory().withClient("sprintplanner-web")
		.secret(passwordEncoder.encode("")).scopes("trusted")
		.authorizedGrantTypes("password", "refresh_token")
		.accessTokenValiditySeconds(accessTokenValiditySeconds)
		.refreshTokenValiditySeconds(refreshTokenValiditySeconds);
    }

    /**
     * Standard enpoint returning a view of the current authenticated user.
     * <p>
     * Could be in a "user controller".
     *
     * @param authentication injected authentication object
     * @return a view of the current authenticated user
     */
    @GetMapping("/userInfo")
    public CustomUserInfoDto userInfo() {
	Long userId = SecurityHelper.getUserId();
	return userDetailsService.getCurrentUserInfo(userId);
    }
}
