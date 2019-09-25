package fr.vilth.sprintplanner.api.services;


import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.MemberCreateDto;

/**
 * Service to handle {@code Member} persistence.
 * 
 * @author Thierry VILLEPREUX
 *
 */
public interface MemberService {

    /**
     * Persists given {@code MemberCreateDto}.
     * @param member the given {@code MemberCreateDto}
     * @return the attributed {@code id} encapsulated in an {@code EntityIdDto}
     */
    EntityIdDto save(MemberCreateDto member);
}
