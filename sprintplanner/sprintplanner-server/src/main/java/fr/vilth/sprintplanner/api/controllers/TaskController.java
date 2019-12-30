package fr.vilth.sprintplanner.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.vilth.sprintplanner.api.services.TaskService;
import fr.vilth.sprintplanner.domain.dtos.task.TaskViewDto;

/**
 * a {@code RestController} to handle {@code Task}.
 * 
 * @author Thierry VILLEPREUX
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    protected TaskController(TaskService taskService) {
	this.taskService = taskService;
    }

    /**
     * Returns a {@code TaskViewDto} by given name
     * 
     * @param taskName the given name
     * @return a {@code TaskViewDto}
     */
    @GetMapping("/{taskName}/name")
    public TaskViewDto getByTaskName(@PathVariable String taskName) {
	return taskService.getByTaskName(taskName);
    }
}
