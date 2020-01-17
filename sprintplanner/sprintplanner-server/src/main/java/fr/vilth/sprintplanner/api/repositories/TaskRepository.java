package fr.vilth.sprintplanner.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.vilth.sprintplanner.domain.entities.Task;

/**
 * {@code JpaRepository} to handle {@code Task} persistence.
 * 
 * @author Thierry VILLEPREUX
 */
public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * Returns a {@code TaskViewDto} by its name
     * 
     * @param name the given name
     * @return a {@code TaskViewDto}
     */
    Task getByName(String name);

    boolean existsByManagerId(Long id);
}
