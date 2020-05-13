package fr.vilth.sprintplanner.api.services;

import javax.mail.MessagingException;

import fr.vilth.sprintplanner.domain.entities.Mail;

public interface EmailService {

    void sendMail(String taskName, Mail mail) throws MessagingException;
}
