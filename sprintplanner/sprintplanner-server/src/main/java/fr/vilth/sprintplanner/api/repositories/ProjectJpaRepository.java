package fr.vilth.sprintplanner.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.vilth.sprintplanner.domain.entities.Project;

/**
 * {@code JpaRepository} to handle {@code Project}
 * 
 * @author Thierry VILLEPREUX
 */
public interface ProjectJpaRepository extends JpaRepository<Project, Long> {

	/**
	 * Find an option of {@code Project} with given Trigram
	 * 
	 * @param trigram the code identifying the application
	 * @return a {@code Project}
	 */
	Optional<Project> findByTrigram(String trigram);
}
