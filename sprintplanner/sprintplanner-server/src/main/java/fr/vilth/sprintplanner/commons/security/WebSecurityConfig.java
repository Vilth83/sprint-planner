package fr.vilth.sprintplanner.commons.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Custom implementation of {@code WebSecurityConfigurerAdapter}
 * 
 * @author vilth
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Defines as Spring bean the authentication manager for this application.
     *
     * @return the authentication manager
     * @see AuthenticationManager#authenticate(org.springframework.security.core.Authentication)
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
	return super.authenticationManagerBean();
    }

    /**
     * Default {@code CorsFilter} bean that configures CORS policies
     * 
     * @return an instance of {@code CorsFilter}
     */
    @Bean
    public CorsFilter corsFilter() {
	final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
	final CorsConfiguration corsConfiguration = new CorsConfiguration();
	corsConfiguration.setAllowCredentials(true);
	corsConfiguration.addAllowedOrigin("http://localhost:4200");
	corsConfiguration.addAllowedHeader("*");
	corsConfiguration.addAllowedMethod("*");
	urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",
		corsConfiguration);
	return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
