package fr.vilth.sprintplanner.configuration.config;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.vilth.sprintplanner.SetupIntTest;
import fr.vilth.sprintplanner.configuration.config.JavaMailConfig;

/**
 * test on JavaMailConfig
 * 
 * @author Thierry VILLEPREUX
 *
 */
public class JavaMailConfigTest extends SetupIntTest {

	@Autowired
	private JavaMailConfig javaMailConfig;

	@Test
	void should_build_mail() {
		Map<String, Object> args = new HashMap<>();
		args.put("releaser", "toto");
		String expected = "Dear <span>toto</span>";
		String actual = javaMailConfig.buildMail(args, "template");
		assertEquals(expected, actual);
	}
}
