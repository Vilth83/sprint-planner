package fr.vilth.sprintplanner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Tests the {@linkplain fr.vilth.sprintplanner.SprintplannerApplication
 * SprintplannerApplication class}.
 * 
 * @author Thierry VILLEPREUX
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@WebAppConfiguration
@ContextConfiguration
public class SprintplannerApplicationTest {

	/**
	 * Should launch the application correctly
	 */
	@Test
	public void contextLoads() {
		//
	}
}
