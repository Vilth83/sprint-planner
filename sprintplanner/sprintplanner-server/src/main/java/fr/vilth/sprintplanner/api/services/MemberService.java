package fr.vilth.sprintplanner.api.services;

import java.util.Set;

import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.member.MemberCreateDto;
import fr.vilth.sprintplanner.domain.dtos.member.MemberDeleteDto;
import fr.vilth.sprintplanner.domain.dtos.member.MemberUpdateDto;
import fr.vilth.sprintplanner.domain.dtos.member.MemberViewDto;
import fr.vilth.sprintplanner.domain.types.Shift;

/**
 * Service to handle {@code Member} persistence.
 * 
 * @author Thierry VILLEPREUX
 */
public interface MemberService {

    /**
     * Persists given {@code MemberCreateDto}.
     * 
     * @param member the given {@code MemberCreateDto}
     * @return the attributed {@code id} encapsulated in an {@code EntityIdDto}
     */
    EntityIdDto save(MemberCreateDto member);

    /**
     * Returns a {@code Set} of {@code MemberViewDto}.
     * 
     * @return a {@code Set} of {@code MemberViewDto}
     */
    Set<MemberViewDto> findAll();

    /**
     * Utility method to validate {@code email} unicity.
     * 
     * @param email the tested {@code email}
     * @return {@code true} if {@code email} exists in database; {@code false}
     *         otherwise
     */
    boolean existsByEmail(String email);

    /**
     * Updates given {@code MemberUpdateDto}.
     * 
     * @param member the given {@code MemberUpdateDto}
     */
    void update(MemberUpdateDto member);

    /**
     * Delete a {@code MemberDeleteDto} by his id.
     * <p>
     * 
     * @param member the given identifier of the {@code Member} encapsulated in
     *        a {@code MemberDeleteDto}.
     */
    void delete(MemberDeleteDto member);

    /**
     * Returns a {@code Set} of {@code MemberViewDto} who respects one of the
     * following conditions :
     * <ul>
     * <li>He is not present in {@code Candidate} table.
     * <li>He is present in {@code Candidate} table for another {@code Task} but
     * not for the given one.
     * </ul>
     * if {@code Shift} is provided, research is limited to given {@code Shift}
     * 
     * @param task the given {@code Task} name.
     * @param shift given {@code Shidt}. Optional
     * @return {@code Set} of {@code Member}
     */
    Set<MemberViewDto> findAllNonCandidatesByTask(String task, Shift shift);
}
