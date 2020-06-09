package fr.vilth.sprintplanner.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.vilth.sprintplanner.domain.entities.Project;

/**
 * {@code JpaRepository} to handle {@code Project}
 * 
 * @author Thierry VILLEPREUX
 */
public interface ProjectJpaRepository extends JpaRepository<Project, Long> {

    /**
     * Find a {@code Project} with given Trigram
     * 
     * @param trigram the code identifying the application
     * @return a {@code Project}
     */
    Project findByTrigram(String trigram);
}
