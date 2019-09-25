package fr.vilth.sprintplanner.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.vilth.sprintplanner.domain.entities.Member;
/**
 * {@code JpaRepository} to handle {@code Member} persistence.
 * 
 * @author Thierry VILLEPREUX
 *
 */
public interface MemberRepository extends JpaRepository<Member, Long> {
	//
}
