package fr.vilth.sprintplanner.domain.dtos.member;

import javax.validation.constraints.NotNull;

import fr.vilth.sprintplanner.domain.validators.IsNotCandidate;
import fr.vilth.sprintplanner.domain.validators.IsNotManager;

public class MemberDeleteDto {

    @IsNotCandidate
    @IsNotManager
    @NotNull
    private Long id;
}
