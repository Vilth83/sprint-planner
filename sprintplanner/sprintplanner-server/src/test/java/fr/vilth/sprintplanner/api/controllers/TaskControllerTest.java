package fr.vilth.sprintplanner.api.controllers;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.vilth.sprintplanner.SetupIntTest;
import fr.vilth.sprintplanner.domain.dtos.task.TaskViewDto;

/**
 * Test upon {@code TaskController}.
 * 
 * @author Thierry VILLEPREUX
 */
public class TaskControllerTest extends SetupIntTest {

    @Autowired
    private TaskController controller;

    @Test
    void should_return_task_by_name() {
	TaskViewDto tested = controller.getByTaskName("releaser");
	assertNotNull(tested);
    }
}
