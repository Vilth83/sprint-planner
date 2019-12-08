package fr.vilth.sprintplanner.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.vilth.sprintplanner.domain.entities.Candidate;

/**
 * {@code JpaRepository} to handle {@code Candidate} persistence.
 * 
 * @author Thierry VILLEPREUX
 */
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    //
}
