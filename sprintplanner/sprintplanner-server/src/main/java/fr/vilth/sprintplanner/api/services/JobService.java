package fr.vilth.sprintplanner.api.services;

import fr.vilth.sprintplanner.domain.dtos.job.JobUpdateDto;
import fr.vilth.sprintplanner.domain.entities.Job;

public interface JobService {

    Job findByTitleAndTask(String title, String taskName);

    void updateJob(JobUpdateDto status);
}
