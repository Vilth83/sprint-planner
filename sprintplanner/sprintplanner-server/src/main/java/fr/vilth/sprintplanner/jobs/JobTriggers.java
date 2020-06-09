package fr.vilth.sprintplanner.jobs;

import javax.mail.MessagingException;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fr.vilth.sprintplanner.api.services.JobService;
import fr.vilth.sprintplanner.commons.utils.Constants;
import fr.vilth.sprintplanner.domain.entities.Job;
import fr.vilth.sprintplanner.domain.entities.Mail;

/**
 * Service activating job at scheduled time.
 * <p>
 * Scheduling is done with CRON expressions passed in application.yml.
 * <ul>
 * Autowires :
 * <li>{@code EmailJob} to build and send mails
 * <li>{@code ReleaseJob} for round robin and releaase incrementation
 * <li>{@code JobService} to check if given job is active before executing it
 *
 * @author Thierry VILLEPREUX
 */
@Service
public class JobTriggers {

    private final EmailJob emailJob;

    private final ReleaseJob releaseJob;

    private final JobService jobService;

    /**
     * Constructor for JobTriggers.
     * <p>
     * Autowires required service {@code Beans}
     * 
     * @param emailJob {@code Autowired}
     * @param releaseJob {@code Autowired}
     * @param jobService {@code Autowired}
     */
    public JobTriggers(EmailJob emailJob, ReleaseJob releaseJob,
	    JobService jobService) {
	super();
	this.emailJob = emailJob;
	this.releaseJob = releaseJob;
	this.jobService = jobService;
    }

    /**
     * Scheduled task to send releaser mail.
     * 
     * @throws MessagingException if error with {@code JavaMailSender}
     */
    @Scheduled(cron = "${cron.config.send-releaser-mail}")
    public void informRelease() throws MessagingException {
	Job job = jobService.findByTitleAndTask("mail",
		Constants.RELEASER);
	if (job.isActive()) {
	    Mail mail = emailJob.buildMail(Constants.RELEASER);
	    emailJob.send(mail);
	}
    }

    /**
     * Scheduled task to send support mail.
     * 
     * @throws MessagingException if error with {@code JavaMailSender}
     */
    @Scheduled(cron = "${cron.config.send-support-mail}")
    public void informSupport() throws MessagingException {
	Job job = jobService.findByTitleAndTask("mail",
		Constants.SUPPORT);
	if (job.isActive()) {
	    Mail mail = emailJob.buildMail(Constants.SUPPORT);
	    emailJob.send(mail);
	}
    }

    /**
     * Scheduled round robin task for release.
     * <p>
     * make releaser rotation and increment release version number.
     * 
     * @throws MessagingException if error with {@code JavaMailSender}
     */
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

    /**
     * Scheduled round robin task for support.
     * <p>
     * make support rotation for every shift and every support task
     * 
     * @throws MessagingException if error with {@code JavaMailSender}
     */
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
