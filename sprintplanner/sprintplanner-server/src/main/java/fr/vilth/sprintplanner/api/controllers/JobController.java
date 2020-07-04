package fr.vilth.sprintplanner.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.vilth.sprintplanner.api.services.JobService;
import fr.vilth.sprintplanner.domain.dtos.job.JobUpdateDto;
import fr.vilth.sprintplanner.domain.entities.Job;
import fr.vilth.sprintplanner.security.annotations.HasRoleAdmin;

/**
 * A {@code RestController} to handle {@code Job}
 * 
 * @author Thierry VILLEPREUX
 */
@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    /**
     * Protected constructor that autowires needed beans.
     * <p>
     * Injects {@code JobService} required bean
     * 
     * @param jobService the injected {@code JobService}
     */
    protected JobController(JobService jobService) {
	this.jobService = jobService;
    }

    /**
     * Returns job by its title and task
     * 
     * @param title the title of a job
     * @param task the task concerned by the job
     * @return a {@code Job}
     */
    @GetMapping("/{title}")
    public Job findByTitleAndTaskName(@PathVariable String title,
	    @RequestParam String task) {
	return jobService.findByTitleAndTask(title, task);
    }

    /**
     * Updates {@code Job} active status.
     * 
     * @param status a {@code JobUpdateDto} representing the modifications for a
     *        given {@code Job}
     */
    @PutMapping
    @HasRoleAdmin
    public void updateJob(@RequestBody JobUpdateDto status) {
	jobService.updateJob(status);
    }
}
