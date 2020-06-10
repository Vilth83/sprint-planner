package fr.vilth.sprintplanner.external_apis.github.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Representation of a github {@code Branch}
 * 
 * @author Thierry VILLEPREUX
 */
public class Branch {

    @SuppressWarnings("unused") // Required for Jackson mapping
    private String name;

    @SuppressWarnings("unused") // Required for Jackson mapping
    private Commit commit;

    @JsonProperty("protected")
    private boolean protection;
}
