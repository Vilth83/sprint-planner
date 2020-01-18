package fr.vilth.sprintplanner.domain.dtos.member;

import javax.validation.constraints.NotNull;

import fr.vilth.sprintplanner.domain.validators.NotCandidate;
import fr.vilth.sprintplanner.domain.validators.NotManager;

/**
 * Data Transfer Object representing a {@code Member} to be deleted.
 * <p>
 * constraints are :
 * <ul>
 * <li>the id must not be null</li>
 * <li>the {@code Member} to be deleted must not be {@code Candidate} for any
 * {@code Task}</li>
 * <li>the {@code Member} to be deleted must not be manager for any
 * {@codeTask}</li>
 * </ul>
 * 
 * @author Thierry VILLEPREUX
 */
public class MemberDeleteDto {

    @NotCandidate
    @NotManager
    @NotNull
    private Long id;
}
