package fr.vilth.sprintplanner.github.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Branch {

    private String name;

    private Commit commit;

    @JsonProperty("protected")
    private boolean protection;
}
