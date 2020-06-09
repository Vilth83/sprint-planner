package fr.vilth.sprintplanner.api.services;

import fr.vilth.sprintplanner.domain.dtos.job.JobUpdateDto;
import fr.vilth.sprintplanner.domain.entities.Job;

/**
 * {@code Service} to handle {@code Job} retrieval
 * 
 * @author Thierry VILLEPREUX
 */
public interface JobService {

    /**
     * Retrieves a {@code Job} by given title and task
     * 
     * @param title the given title
     * @param taskName the given task name
     * @return a {@code Job}
     */
    Job findByTitleAndTask(String title, String taskName);

    /**
     * Updates a {@code Job} based on given {@code JobUpdateDto}
     * <p>
     * The only updated information is the {@code Job} status (active or not).
     *
     * @param status the {@code JobDateDto}
     */
    void updateJob(JobUpdateDto status);
}
