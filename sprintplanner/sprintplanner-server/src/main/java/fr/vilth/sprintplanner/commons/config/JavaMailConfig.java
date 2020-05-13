package fr.vilth.sprintplanner.commons.config;

import java.util.Map;
import java.util.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Configuration
public class JavaMailConfig {

    private String host;

    private String protocol;

    private int port;

    private final TemplateEngine templateEngine;

    public JavaMailConfig(TemplateEngine templateEngine) {
	this.templateEngine = templateEngine;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.mail")
    public JavaMailSender getJavaMailSender() {
	final JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
	javaMailSender.setHost(host);
	javaMailSender.setProtocol(protocol);
	javaMailSender.setPort(port);
	final Properties props = javaMailSender.getJavaMailProperties();
	props.put("mail.smtp.auth", "false");
	props.put("mail.smtp.starttls", "false");
	props.put("mail.debug", "false");
	return javaMailSender;
    }

    public String buildMail(Map<String, Object> arguments, String template) {
	Context context = new Context();
	context.setVariables(arguments);
	return templateEngine.process(template, context);
    }
}
