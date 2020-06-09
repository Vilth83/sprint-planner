package fr.vilth.sprintplanner.api.services;

import javax.mail.MessagingException;

import fr.vilth.sprintplanner.domain.entities.Mail;

public interface EmailService {

    void sendMail(Mail mail) throws MessagingException;
}
