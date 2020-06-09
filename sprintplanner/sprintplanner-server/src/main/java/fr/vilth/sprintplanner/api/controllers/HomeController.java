package fr.vilth.sprintplanner.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * A {@code Controller} to bootstrap the packaged application.
 * <p>
 * Required to allow a call to the API uri being forwarded to Angular
 * application index.
 * 
 * @author Thierry VILLEPREUX
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    /**
     * Forwards the request to the index.html root page of the Angular
     * application.
     * 
     * @return the forward to index.html
     */
    @GetMapping
    public String home() {
	return "forward:/index.html";
    }
}
