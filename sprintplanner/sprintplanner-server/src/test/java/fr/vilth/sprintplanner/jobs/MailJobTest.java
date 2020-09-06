package fr.vilth.sprintplanner.jobs;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.hibernate.cfg.NotYetImplementedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.vilth.sprintplanner.SetupIntTest;
import fr.vilth.sprintplanner.configuration.utils.Constants;
import fr.vilth.sprintplanner.domain.entities.Mail;

public class MailJobTest extends SetupIntTest {

    @Autowired
    private EmailJob emailJob;

    @Test
    void should_return_releaser_mail() {
	Mail mail = emailJob.buildMail(Constants.RELEASER);
	String sender = "sprintplanner@bot";
	List<String> recipients = Arrays.asList("second@member", "third@member",
		"task@mail");
	String subject = "[SPRINTPLANNER] Releaser and tester of the week have been selected for version v";
	Assertions.assertAll(
		() -> assertEquals(sender, mail.getSender()),
		() -> assertEquals(recipients, mail.getRecipients()),
		() -> Assertions
			.assertTrue(mail.getSubject().contains(subject)));
    }

    @Test
    void should_return_support_mail() {
	Mail mail = emailJob.buildMail(Constants.SUPPORT);
	String sender = "sprintplanner@bot";
	List<String> recipients = Arrays.asList("third@member", "fourth@member",
		"first@member", "second@member",
		"support@mail");
	String subject = "[SPRINTPLANNER] Support of the day have been selected for version v";
	Assertions.assertAll(
		() -> assertEquals(sender, mail.getSender()),
		() -> assertEquals(recipients, mail.getRecipients()),
		() -> Assertions
			.assertTrue(mail.getSubject().contains(subject)));
    }

    @Test
    void should_fail_building_mail_with_wrong_task() {
	Assertions.assertThrows(NotYetImplementedException.class,
		() -> emailJob.buildMail("WRONG"));
    }
}
