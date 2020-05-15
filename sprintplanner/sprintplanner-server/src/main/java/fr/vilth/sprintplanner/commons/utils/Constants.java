package fr.vilth.sprintplanner.commons.utils;

public class Constants {

    public static final String RELEASER = "releaser";

    public static final String TESTER = "tester";

    public static final String SUPPORT = "support";

    public static final String TECHNICAL = "technical";

    public static final String FUNCTIONAL = "functional";

    public static final String CANDIDATE_NAME_QUERY = // -
	    "select new fr.vilth.sprintplanner.domain.dtos.candidate.CandidateNameDto(m.firstname, m.lastname) "
		    + "from Member m join Candidate c on m.id = c.member join Task t on t.id = c.task "
		    + "where t.name = :taskName and c.status = :status";

    public static final String FIRST_ELIGIBLE_CANDIDATE = "select c from Member m join Candidate c on m.id = c.member "
	    + "join Task t on t.id = c.task where t.name = :task and c.status = :status and (:shift is null "
	    + "or m.shift = :shift) order by c.priority desc";
}
