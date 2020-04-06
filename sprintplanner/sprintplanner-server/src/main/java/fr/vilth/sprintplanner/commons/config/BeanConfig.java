package fr.vilth.sprintplanner.commons.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
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
