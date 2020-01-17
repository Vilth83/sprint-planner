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

    boolean existByManagerId(Long id);
}
