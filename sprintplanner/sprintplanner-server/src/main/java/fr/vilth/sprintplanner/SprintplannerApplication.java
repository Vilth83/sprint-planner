package fr.vilth.sprintplanner;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Main application class providing spring boot main method and configuration
 * elements.
 * <p>
 * 
 * @author Thierry VILLEPREUX
 */
@SpringBootApplication
public class SprintplannerApplication {

    /**
     * Main method running the entire application.
     * 
     * @param args optional arguments.
     */
    public static void main(String[] args) {
	SpringApplication.run(SprintplannerApplication.class, args);
    }

    /**
     * Custom {@code WebMvcConfigurer} bean.
     * <p>
     * Configures global cross origin requests processing.
     *
     * @return a {@code WebMvcConfigurer}
     */
    @Bean
    protected WebMvcConfigurer webMvcConfigurer() {
	return new WebMvcConfigurer() {

	    @Override
	    public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("http://localhost:4200")
			.allowedMethods("*");
	    }

	    @Override
	    public void addResourceHandlers(
		    final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
			.addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations(
			"classpath:/META-INF/resources/webjars/");
	    }
	};
    }

    /**
     * Default {@code LocalValidatorFactoryBean} to ease messages and tests.
     * <ul>
     * <li>Tell Spring to use the application {@code messages.properties}.</li>
     * <li>Give convenient validator bean to test DTO validation
     * annotations.</li>
     * </ul>
     * 
     * @param messageSource a message source strategy
     * @return a validator factory
     */
    @Bean
    protected LocalValidatorFactoryBean validator(MessageSource messageSource) {
	LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
	validatorFactoryBean.setValidationMessageSource(messageSource);
	return validatorFactoryBean;
    }

    /**
     * Default {@code ModelMapper} bean that configures mapping between DTO and
     * entities.
     * <p>
     * field matching is enabled with private access and standard matching
     * strategy.
     * 
     * @return an instance of {@code ModelMapper}
     */
    @Bean
    protected ModelMapper modelMapper() {
	ModelMapper mapper = new ModelMapper();
	mapper.getConfiguration().setFieldMatchingEnabled(true)
		.setFieldAccessLevel(AccessLevel.PRIVATE)
		.setMatchingStrategy(MatchingStrategies.STANDARD);
	return mapper;
    }

    /**
     * Default {@code ObjectMapper} bean that configures mapping between JSON
     * and DTO.
     * <p>
     * Set mapping to field with any visibility and deactivates mapping with
     * accessors/mutators. Configures dates serialization using
     * {@code JavaTimeModule}.
     * 
     * @return an instance of {@code ObjectMapper}.
     */
    @Bean
    protected ObjectMapper objectMapper() {
	ObjectMapper mapper = new ObjectMapper();
	mapper.setVisibility(
		mapper.getSerializationConfig().getDefaultVisibilityChecker()
			.withFieldVisibility(JsonAutoDetect.Visibility.ANY)
			.withGetterVisibility(JsonAutoDetect.Visibility.NONE)
			.withIsGetterVisibility(JsonAutoDetect.Visibility.NONE)
			.withSetterVisibility(JsonAutoDetect.Visibility.NONE));
	mapper.registerModule(new JavaTimeModule());
	mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	return mapper;
    }
}
