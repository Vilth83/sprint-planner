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

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
	this.jobService = jobService;
    }

    @GetMapping("/{title}")
    public Job findByTitleAndTaskName(@PathVariable String title,
	    @RequestParam String task) {
	return jobService.findByTitleAndTask(title, task);
    }

    @PutMapping
    public void updateJob(@RequestBody JobUpdateDto status) {
	jobService.updateJob(status);
    }
}
