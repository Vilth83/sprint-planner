package fr.vilth.sprintplanner.api.services;

import java.util.Set;

import fr.vilth.sprintplanner.api.controllers.MemberViewDto;
import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.MemberCreateDto;
import fr.vilth.sprintplanner.domain.dtos.MemberUpdateDto;

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
	 * Persists given {@code MemberUpdateDto}.
	 * 
	 * @param member the given {@code MemberViewDto}
	 */
	void update(MemberUpdateDto member);

}
