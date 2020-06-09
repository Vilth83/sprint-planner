package fr.vilth.sprintplanner.domain.entities;

import java.util.List;

/**
 * Representation of a mail.
 * <p>
 * Contains all needed informations for {@code JavaMailSender} to send a mail.
 * 
 * @author Thierry VILLEPREUX
 */
public class Mail {

    private String sender;

    private List<String> recipients;

    private String subject;

    private String content;

    /**
     * Constructs a new instance of {@code Mail}
     * 
     * @param sender the email address of the sender
     * @param recipients the {@code List} of recipient's emails
     * @param subject the subject of the mail
     * @param content the text to be sent
     */
    public Mail(String sender, List<String> recipients, String subject,
	    String content) {
	this.sender = sender;
	this.recipients = recipients;
	this.subject = subject;
	this.content = content;
    }

    /**
     * Getter for sender email
     * 
     * @return sender
     */
    public String getSender() {
	return sender;
    }

    /**
     * Getter for recipients
     * 
     * @return a {@code List} of {@code String}
     */
    public List<String> getRecipients() {
	return recipients;
    }

    /**
     * Getter for subject
     * 
     * @return the subject
     */
    public String getSubject() {
	return subject;
    }

    /**
     * Getter for content
     * 
     * @return the content
     */
    public String getContent() {
	return content;
    }
}
