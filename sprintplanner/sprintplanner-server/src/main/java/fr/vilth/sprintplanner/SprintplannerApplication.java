package fr.vilth.sprintplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * Main application class providing spring boot main method and configuration
 * elements.
 * <p>
 * 
 * @author Thierry VILLEPREUX
 */
@SpringBootApplication
@EnableScheduling
public class SprintplannerApplication {

    /**
     * Main method running the entire application.
     * 
     * @param args optional arguments.
     */
    public static void main(String[] args) {
	SpringApplication.run(SprintplannerApplication.class, args);
    }
}
