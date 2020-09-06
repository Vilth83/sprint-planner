package fr.vilth.sprintplanner.configuration.config;

import java.util.Map;
import java.util.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Configuration Class providing {@code JavaMailSender}.
 * <p>
 * Configures and provides {@code JavaMailSender} with host, protocol and port
 * and convenient method to build a {@code Mail} template.
 * 
 * @author vilth
 */
@Configuration
public class JavaMailConfig {

    private String host;

    private String protocol;

    private int port;

    private final TemplateEngine templateEngine;

    private static final String FALSE = "false";

    /**
     * Protected constructor to autowire needed bean.
     * <p>
     * injects {@code TemplateEngine} to build {@code Mail} template.
     * 
     * @param templateEngine
     */
    protected JavaMailConfig(TemplateEngine templateEngine) {
	this.templateEngine = templateEngine;
    }

    /**
     * Configures and return {@code JavaMailSender}.
     * <p>
     * Reads properties to retrieve needed fields, set host, protocol, port and
     * properties to the returned {@code JavaMailSender}.
     *
     * @return the {@code JavaMailSender}
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.mail")
    public JavaMailSender getJavaMailSender() {
	final JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
	javaMailSender.setHost(host);
	javaMailSender.setProtocol(protocol);
	javaMailSender.setPort(port);
	final Properties props = javaMailSender.getJavaMailProperties();
	props.put("mail.smtp.auth", FALSE);
	props.put("mail.smtp.starttls", FALSE);
	props.put("mail.debug", FALSE);
	return javaMailSender;
    }

    /**
     * Utility method to build a template with arguments.
     * <p>
     * find given template in resources by its name and set arguments to it.
     * Arguments are set by their key (written in the template) replacing them
     * by the value.
     * 
     * @param arguments a map containing arguments key/ value
     * @param template the template name to search in resources folder
     * @return the template populated with arguments values
     */
    public String buildMail(Map<String, Object> arguments, String template) {
	Context context = new Context();
	context.setVariables(arguments);
	return templateEngine.process(template, context);
    }
}
