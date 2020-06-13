package fr.vilth.sprintplanner.commons.utils;

/**
 * Utility class that provides hard coded {@code String} expressions.
 * <p>
 * Using this utility class ensures consistency in the code : "magical Strings"
 * should not be provided anywhere else in this application
 * 
 * @author Thierry VILLEPREUX
 */
public final class Constants {

    private Constants() {
	// private Constructor to ensure non-instantiability
    }
    /**
     * releaser task {@code String}
     */
    public static final String RELEASER = "releaser";

    /**
     * tester task {@code String}
     */
    public static final String TESTER = "tester";

    /**
     * support task {@code String}
     */
    public static final String SUPPORT = "support";

    /**
     * technical task {@code String} for support
     */
    public static final String TECHNICAL = "technical";

    /**
     * functional task {@code String} for support
     */
    public static final String FUNCTIONAL = "functional";
}
