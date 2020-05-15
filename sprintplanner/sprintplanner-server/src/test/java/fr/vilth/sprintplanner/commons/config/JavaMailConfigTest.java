package fr.vilth.sprintplanner.commons.config;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.vilth.sprintplanner.SetupIntTest;

public class JavaMailConfigTest extends SetupIntTest {

    @Autowired
    private JavaMailConfig javaMailConfig;

    @Test
    void test() {
	Map<String, Object> args = new HashMap<>();
	args.put("releaser", "toto");
	String expected = "Dear <span>toto</span>";
	String actual = javaMailConfig.buildMail(args, "template");
	assertEquals(expected, actual);
    }
}
