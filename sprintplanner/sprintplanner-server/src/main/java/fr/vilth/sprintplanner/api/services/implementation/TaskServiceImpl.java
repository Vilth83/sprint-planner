package fr.vilth.sprintplanner.api.services.implementation;

import org.springframework.stereotype.Service;

import fr.vilth.sprintplanner.api.repositories.TaskJpaRepository;
import fr.vilth.sprintplanner.api.services.TaskService;
import fr.vilth.sprintplanner.commons.api.AbstractService;
import fr.vilth.sprintplanner.domain.dtos.task.TaskViewDto;
import fr.vilth.sprintplanner.domain.entities.Task;

/**
 * Default concrete implementation of {@code TaskService}.
 * 
 * @author Thierry VILLEPREUX
 */
@Service
public class TaskServiceImpl extends AbstractService implements TaskService {

    private final TaskJpaRepository taskRepository;

    /**
     * Protected constructor to autowire needed bean.
     * <p>
     * injects {@code TaskRepository} interface to persist and retrieve
     * {@code Task}.
     * 
     * @param taskRepository the injected {@code TaskRepository}
     */
    public TaskServiceImpl(TaskJpaRepository taskRepository) {
	this.taskRepository = taskRepository;
    }

    public TaskViewDto getByTaskName(String taskName) {
	Task task = taskRepository.getByName(taskName);
	return modelMapper.map(task, TaskViewDto.class);
    }

    @Override
    public boolean existByManagerId(Long id) {
	return taskRepository.existsByManagerId(id);
    }
}
