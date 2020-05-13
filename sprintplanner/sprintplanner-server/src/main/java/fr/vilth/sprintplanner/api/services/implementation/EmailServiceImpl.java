package fr.vilth.sprintplanner.api.services.implementation;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import fr.vilth.sprintplanner.api.services.EmailService;
import fr.vilth.sprintplanner.domain.entities.Mail;

@Service
public class EmailServiceImpl implements EmailService {

    private static final String ENCODING = "utf-8";

    private static final String TYPE = "text/html";

    private final JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
	this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendMail(String taskName, Mail mail) throws MessagingException {
	MimeMessage message = javaMailSender.createMimeMessage();
	MimeMessageHelper helper = new MimeMessageHelper(message, true,
		"utf-8");
	helper.setFrom(mail.getSender());
	helper.setTo(mail.getRecipients().toArray(new String[0]));
	helper.setSubject(mail.getSubject());
	message.setContent(mail.getContent(), "text/html");
	javaMailSender.send(message);
    }
}
