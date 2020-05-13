package fr.vilth.sprintplanner.jobs;

import javax.mail.MessagingException;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fr.vilth.sprintplanner.api.services.JobService;
import fr.vilth.sprintplanner.commons.utils.Constants;
import fr.vilth.sprintplanner.domain.entities.Job;

@Service
public class JobTriggers {

    private final EmailJob emailJob;

    private final ReleaseJob releaseJob;

    private final JobService jobService;

    public JobTriggers(EmailJob emailJob, ReleaseJob releaseJob,
	    JobService jobService) {
	super();
	this.emailJob = emailJob;
	this.releaseJob = releaseJob;
	this.jobService = jobService;
    }

    @Scheduled(cron = "${cron.config.send-releaser-mail}")
    public void informRelease() throws MessagingException {
	Job job = jobService.findByTitleAndTask("mail",
		Constants.RELEASER);
	if (job.isActive()) {
	    emailJob.buildAndSend(Constants.RELEASER);
	}
    }

    @Scheduled(cron = "${cron.config.send-support-mail}")
    public void informSupport() throws MessagingException {
	Job job = jobService.findByTitleAndTask("mail",
		Constants.SUPPORT);
	if (job.isActive()) {
	    emailJob.buildAndSend(Constants.SUPPORT);
	}
    }

    @Scheduled(cron = "${cron.config.handle-release}")
    public void handleRelease() {
	Job job = jobService.findByTitleAndTask("mail",
		Constants.RELEASER);
	// first, the releaser is changed
	if (job.isActive()) {
	    releaseJob.rotateReleasers();
	}
	// then, the release is incremented
	releaseJob.incrementReleaseVersion();
    }

    @Scheduled(cron = "${cron.config.handle-support}")
    public void handleSupport() {
	// the support is changed for each shift and task
	Job job = jobService.findByTitleAndTask("roundRobin",
		Constants.SUPPORT);
	if (job.isActive()) {
	    releaseJob.rotateSupport(Constants.TECHNICAL);
	    releaseJob.rotateSupport(Constants.FUNCTIONAL);
	}
    }
}
