package fr.vilth.sprintplanner.api.services.implementation;

import org.springframework.stereotype.Service;

import fr.vilth.sprintplanner.api.repositories.JobJpaRepository;
import fr.vilth.sprintplanner.api.services.JobService;
import fr.vilth.sprintplanner.commons.api.AbstractService;
import fr.vilth.sprintplanner.domain.dtos.job.JobUpdateDto;
import fr.vilth.sprintplanner.domain.entities.Job;

@Service
public class JobServiceImpl extends AbstractService implements JobService {

    private final JobJpaRepository jobRepository;

    public JobServiceImpl(JobJpaRepository jobRepository) {
	this.jobRepository = jobRepository;
    }

    @Override
    public Job findByTitleAndTask(String title, String taskName) {
	return jobRepository.findByTitleAndTask(title, taskName);
    }

    @Override
    public void updateJob(JobUpdateDto inputs) {
	Job job = jobRepository.findByTitleAndTask(inputs.getTitle(),
		inputs.getTask());
	modelMapper.map(inputs, job);
	jobRepository.save(job);
    }
}
