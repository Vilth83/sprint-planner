package fr.vilth.sprintplanner.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
}
