package fr.vilth.sprintplanner.api.services;

import fr.vilth.sprintplanner.domain.dtos.task.TaskViewDto;

/**
 * Service to handle {@code Task} persistence.
 * 
 * @author Thierry VILLEPREUX
 */
public interface TaskService {

    /**
     * Returns a {@code TaskViewDto} by its name
     * 
     * @param taskName the given name
     * @return a {@code TaskViewDto}
     */
    TaskViewDto getByTaskName(String taskName);

    /**
     * Returns wether or not a {@code Task} exists with given {@code Member} id
     * as manager.
     * 
     * @param id the identifier of a {@code Member}
     * @return {@code true} if a {@code Task} exists with given {@code Member}
     *         id; {@code false} otherwise
     */
    boolean existByManagerId(Long id);
}
