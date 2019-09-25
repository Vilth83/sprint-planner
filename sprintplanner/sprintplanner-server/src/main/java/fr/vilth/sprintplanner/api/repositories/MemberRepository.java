package fr.vilth.sprintplanner.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.vilth.sprintplanner.domain.entities.Member;

/**
 * {@code JpaRepository} to handle {@code Member} persistence.
 * 
 * @author Thierry VILLEPREUX
 */
public interface MemberRepository extends JpaRepository<Member, Long> {

    /**
     * Return either the {@code email} is unique or not.
     * 
     * @param email the tested {@code email}
     * @return {@code true} if {@code email} exists; {@code false} otherwise.
     */
    boolean existsByEmail(String email);
    //
}
