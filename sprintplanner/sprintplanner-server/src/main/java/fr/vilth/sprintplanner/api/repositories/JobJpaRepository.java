package fr.vilth.sprintplanner.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.vilth.sprintplanner.domain.entities.Job;

/**
 * {@code JpaRepository} to handle {@code Job}
 * 
 * @author Thierry VILLEPREUX
 */
public interface JobJpaRepository extends JpaRepository<Job, Long> {

    /**
     * Retrieves a {@code Job} by given title and task
     * 
     * @param title the given title
     * @param taskName the given task name
     * @return a {@code Job}
     */
    Job findByTitleAndTask(String title, String taskName);
}
