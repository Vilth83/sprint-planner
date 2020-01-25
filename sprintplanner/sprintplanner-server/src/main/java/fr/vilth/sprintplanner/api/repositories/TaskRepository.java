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

    /**
     * Returns wether or not a {@code Task} exists with given {@code Member} id
     * as manager.
     * 
     * @param id the identifier of a {@code Member}
     * @return {@code true} if a {@code Task} exists with given {@code Member}
     *         id; {@code false} otherwise
     */
    boolean existsByManagerId(Long id);
}
