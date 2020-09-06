package fr.vilth.sprintplanner.configuration.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuration class declaring configuration beans.
 * <p>
 * Provides default configuration for beans such as {@code ObjectMapper},
 * {@code ModelMapper} or {@code RestTemplate}. Please note that those beans are
 * configured for the sake of all this application and SHOULD NOT be overridden.
 * 
 * @author Thierry VILLEPREUX
 */
@Configuration
@EnableSwagger2
public class BeanConfig {

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
	ObjectMapper mapper = Jackson2ObjectMapperBuilder.json().build();
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

    /**
     * Default configuration of a {@code RestTemplate}.
     * <p>
     * No specific configuration is needed, so this {@code Bean} returns a
     * standard {@code RestTemplate}
     * 
     * @param builder the {@code RestTemplateBuilder} needed to build a
     *        {@code RestTemplate}
     * @return {@code RestTemplate}
     */
    @Bean
    public RestTemplate template(RestTemplateBuilder builder) {
	return builder.build();
    }

    /**
     * Configures {@code SWAGGER} api {@code Docket}.
     * <p>
     * Defines basePackage as "fr.vilth", meaning that every
     * {@code RestController} is scanned and retrieved by SWAGGER ui.
     * 
     * @return a new {@code Docket}
     */
    @Bean
    public Docket api() {
	return new Docket(DocumentationType.SWAGGER_2).select()
		.apis(RequestHandlerSelectors.basePackage("fr.vilth"))
		.paths(PathSelectors.any()).build();
    }

    /**
     * The password encoder bean for the application. Used for client and users.
     *
     * @return a password encoder
     */
    @Bean
    protected PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
    }
}
