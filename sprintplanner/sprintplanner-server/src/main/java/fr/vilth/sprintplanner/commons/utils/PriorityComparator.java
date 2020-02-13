package fr.vilth.sprintplanner.commons.utils;

import java.io.Serializable;
import java.util.Comparator;

import fr.vilth.sprintplanner.domain.entities.Candidate;

public class PriorityComparator implements Comparator<Candidate>, Serializable {

    private static final long serialVersionUID = -6090318391952772657L;

    @Override
    public int compare(Candidate candidate, Candidate other) {
	return candidate.getPriority() - other.getPriority();
    }
}
