package fr.vilth.sprintplanner.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.vilth.sprintplanner.domain.entities.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
