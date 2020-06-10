package fr.vilth.sprintplanner.commons.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web configuration.
 * <p>
 * Implements {@code WebMvcConfigurer} to configure base path and resource
 * handlers.
 *
 * @author Thierry VILLEPREUX
 *
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	/**
	 * Defines the "/api" prefix for all {@code @RestController} in the application.
	 * <p>
	 * Configured to prevent conflicts and ease configuration with oauth
	 * authentication endpoints.
	 *
	 * @param configurer a path configurer
	 */
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.addPathPrefix("/api", HandlerTypePredicate.forAnnotation(RestController.class));
	}

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
