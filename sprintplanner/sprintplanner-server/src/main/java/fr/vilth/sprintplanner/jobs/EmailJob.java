package fr.vilth.sprintplanner.jobs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.stereotype.Component;

import fr.vilth.sprintplanner.api.services.CandidateService;
import fr.vilth.sprintplanner.api.services.EmailService;
import fr.vilth.sprintplanner.api.services.ProjectService;
import fr.vilth.sprintplanner.api.services.ReleaseService;
import fr.vilth.sprintplanner.api.services.TaskService;
import fr.vilth.sprintplanner.configuration.config.JavaMailConfig;
import fr.vilth.sprintplanner.configuration.utils.Constants;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateViewDto;
import fr.vilth.sprintplanner.domain.dtos.member.MemberViewDto;
import fr.vilth.sprintplanner.domain.dtos.project.ProjectViewDto;
import fr.vilth.sprintplanner.domain.dtos.release.ReleaseViewDto;
import fr.vilth.sprintplanner.domain.entities.Mail;
import fr.vilth.sprintplanner.domain.types.Shift;
import fr.vilth.sprintplanner.domain.types.Status;

/**
 * Component building mail to be sent by scheduled jobs.
 * <p>
 * build mail with given taskname, by retrieving recipients, subject, template
 * and building a {@code Mail} with {@code JavaMailBuilder}
 * <p>
 * Please not that this class is annotated {@code @Component} to autowire
 * services and thus prevent to autowire them in {@code Jobtriggers}
 * 
 * @author Thierry VILLEPREUX
 */
@Component
public class EmailJob {

    private final ProjectService projectService;

    private final CandidateService candidateService;

    private final TaskService taskService;

    private final JavaMailConfig javaMailBuilder;

    private final ReleaseService releaserService;

    private final EmailService emailService;

    /**
     * Autowires required service {@code Beans}
     * 
     * @param projectService {@code Autowired}
     * @param candidateService {@code Autowired}
     * @param taskService {@code Autowired}
     * @param javaMailBuilder {@code Autowired}
     * @param releaserService {@code Autowired}
     * @param emailService {@code Autowired}
     */
    public EmailJob(ProjectService projectService,
	    CandidateService candidateService, TaskService taskService,
	    JavaMailConfig javaMailBuilder, ReleaseService releaserService,
	    EmailService emailService) {
	this.projectService = projectService;
	this.candidateService = candidateService;
	this.taskService = taskService;
	this.javaMailBuilder = javaMailBuilder;
	this.releaserService = releaserService;
	this.emailService = emailService;
    }

    private String getSender() {
	ProjectViewDto project = projectService.getProject();
	return project.getEmail();
    }

    /**
     * send a {@code Mail}.
     * 
     * @param mail the {@code Mail} to be sent
     * @throws MessagingException thrown by JavaMailSender if any error while
     *         creating or sending {@code MimeMessage}
     */
    public void send(Mail mail) throws MessagingException {
	emailService.sendMail(mail);
    }

    /**
     * Build a {@code Mail} for given task.
     * <p>
     * Retrieves sender and subject, constructs subject regarding task,
     * retrieves recipients and constructs content.
     * 
     * @param taskName the givent task
     * @return a {@code Mail}
     */
    public Mail buildMail(String taskName) {
	String sender = getSender();
	String template = taskName + "_mail";
	String subject = getSubject(taskName);
	Map<String, CandidateViewDto> candidates = getCandidates(taskName);
	List<String> recipients = getRecipients(taskName, candidates);
	String content = javaMailBuilder.buildMail(getArgs(candidates),
		template);
	return new Mail(sender, recipients, subject, content);
    }

    private String getSubject(String taskName) {
	ReleaseViewDto release = releaserService.findLastRelease();
	String releaseVersion = release.getReleaseVersion();
	String firstPart = taskName.equals(Constants.SUPPORT)
		? "Support of the day"
		: "Releaser and tester of the week";
	String secondPart = " have been selected for version ";
	StringBuilder subject = new StringBuilder("[SPRINTPLANNER] ")
		.append(firstPart).append(secondPart)
		.append(releaseVersion);
	return subject.toString();
    }

    private List<String> getRecipients(String taskName,
	    Map<String, CandidateViewDto> candidates) {
	List<String> recipients = new ArrayList<>();
	candidates.forEach(
		(key, value) -> recipients.add(value.getMember().getEmail()));
	recipients.add(getTaskMail(taskName));
	return recipients;
    }

    private String getTaskMail(String taskName) {
	if (taskName.equals(Constants.SUPPORT)) {
	    return taskService.getByTaskName(Constants.FUNCTIONAL).getEmail();
	}
	return taskService.getByTaskName(Constants.RELEASER).getEmail();
    }

    private Map<String, Object> getArgs(
	    Map<String, CandidateViewDto> candidates) {
	Map<String, Object> args = new HashMap<>();
	candidates.forEach((key, value) -> {
	    final String fullName = buildFullName(value);
	    args.put(key, fullName);
	});
	return args;
    }

    private String buildFullName(CandidateViewDto candidate) {
	MemberViewDto member = candidate.getMember();
	StringBuilder sb = new StringBuilder(member.getFirstname()).append(" ")
		.append(member.getLastname());
	return sb.toString();
    }

    private Map<String, CandidateViewDto> getCandidates(String taskName) {
	Map<String, CandidateViewDto> candidates = new HashMap<>();
	if (taskName.equals(Constants.RELEASER)) {
	    CandidateViewDto releaser = candidateService
		    .findFirstByTaskNameAndStatusAndMemberShift(
			    Constants.RELEASER,
			    Status.CURRENT, null);
	    CandidateViewDto tester = candidateService
		    .findFirstByTaskNameAndStatusAndMemberShift(
			    Constants.TESTER,
			    Status.CURRENT, null);
	    candidates.put(Constants.RELEASER, releaser);
	    candidates.put(Constants.TESTER, tester);
	} else if (taskName.equals(Constants.SUPPORT)) {
	    Arrays.asList(Shift.PAR, Shift.BGL)
		    .forEach(shift -> Arrays
			    .asList(Constants.FUNCTIONAL, Constants.TECHNICAL)
			    .forEach(task -> {
				final CandidateViewDto candidate = candidateService
					.findFirstByTaskNameAndStatusAndMemberShift(
						task, Status.CURRENT, shift);
				candidates.put(task + shift.name(), candidate);
			    }));
	} else {
	    throw new NotYetImplementedException(
		    "given task (" + taskName + ") is not yet handled...");
	}
	return candidates;
    }
}
