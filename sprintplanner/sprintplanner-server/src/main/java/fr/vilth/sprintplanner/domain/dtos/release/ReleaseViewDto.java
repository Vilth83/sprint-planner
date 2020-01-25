package fr.vilth.sprintplanner.domain.dtos.release;

import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateViewDto;

public class ReleaseViewDto {

    private Long id;

    private int pi;

    private int sprint;

    private int week;

    private CandidateViewDto assignee;
}
