package fr.vilth.sprintplanner.domain.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import fr.vilth.sprintplanner.SetupUnitTest;

/**
 * Test on {@code Mail}
 * 
 * @author Thierry VILLEPREUX
 */
public class MailTest extends SetupUnitTest {

    private static final String CONTENT = "content";

    private static final String SUBJECT = "subject";

    private static final String SENDER = "sender@test";

    private static final List<String> RECIPIENTS = new ArrayList<>();

    @BeforeAll
    public static void setUp() {
	RECIPIENTS.add("test");
    }

    @Test
    void should_construct() {
	Mail tested = new Mail(SENDER, RECIPIENTS, SUBJECT, CONTENT);
	assertNotNull(tested);
    }

    @Test
    void should_return_values() {
	Mail tested = new Mail(SENDER, RECIPIENTS, SUBJECT, CONTENT);
	Assertions.assertAll( // -
		() -> assertEquals(SENDER, tested.getSender()),
		() -> assertEquals(RECIPIENTS, tested.getRecipients()),
		() -> assertEquals(SUBJECT, tested.getSubject()),
		() -> assertEquals(CONTENT, tested.getContent())// -
	);
    }
}
