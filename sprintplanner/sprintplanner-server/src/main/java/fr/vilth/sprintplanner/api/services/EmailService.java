package fr.vilth.sprintplanner.api.services;

import javax.mail.MessagingException;

import fr.vilth.sprintplanner.domain.entities.Mail;

/**
 * Service to send {@code Mail}
 * 
 * @author Thierry VILLEPREUX
 */
public interface EmailService {

    /**
     * Send given {@code Mail} with {@code JavaMailSender}
     * 
     * @param mail the given {@code Mail} to be sent
     * @throws MessagingException occurs if error is encountered by
     *         {@code JavaMailService}
     */
    void sendMail(Mail mail) throws MessagingException;
}
