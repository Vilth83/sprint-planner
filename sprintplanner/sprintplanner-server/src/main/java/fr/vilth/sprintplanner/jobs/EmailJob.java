package fr.vilth.sprintplanner.jobs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.context.annotation.Configuration;

import fr.vilth.sprintplanner.api.services.CandidateService;
import fr.vilth.sprintplanner.api.services.EmailService;
import fr.vilth.sprintplanner.api.services.ProjectService;
import fr.vilth.sprintplanner.api.services.ReleaseService;
import fr.vilth.sprintplanner.api.services.TaskService;
import fr.vilth.sprintplanner.commons.config.JavaMailConfig;
import fr.vilth.sprintplanner.commons.utils.Constants;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateViewDto;
import fr.vilth.sprintplanner.domain.dtos.project.ProjectViewDto;
import fr.vilth.sprintplanner.domain.dtos.release.ReleaseViewDto;
import fr.vilth.sprintplanner.domain.entities.Mail;
import fr.vilth.sprintplanner.domain.types.Shift;
import fr.vilth.sprintplanner.domain.types.Status;

@Configuration
public class EmailJob {

    private final ProjectService projectService;

    private final CandidateService candidateService;

    private final TaskService taskService;

    private final JavaMailConfig javaMailBuilder;

    private final ReleaseService releaserService;

    private final EmailService emailService;

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

    public void buildAndSend(String taskName) throws MessagingException {
	Mail mail = buildMail(taskName);
	emailService.sendMail(taskName, mail);
    }

    public Mail buildMail(String taskName) {
	String sender = getSender();
	String template = taskName + "_mail";
	String subject = getSubject(taskName);
	Map<String, CandidateViewDto> candidates = getCandidates(taskName);
	List<String> recipients = getRecipients(taskName, candidates);
	String content = javaMailBuilder.buildMail(getArgs(candidates),
		template);
	return new Mail(sender, recipients, subject,
		content);
    }

    private String getSubject(String taskName) {
	ReleaseViewDto release = releaserService.findLastRelease();
	String releaseVersion = release.getReleaseVersion();
	String firstPart = isSupportTask(taskName) ? "Support of the day"
		: "Releaser and tester of the week";
	String secondPart = " have been selected for version ";
	StringBuffer subject = new StringBuffer("[SPRINTPLANNER] ")
		.append(firstPart).append(secondPart).append(releaseVersion);
	return subject.toString();
    }

    private List<String> getRecipients(String taskName,
	    Map<String, CandidateViewDto> candidates) {
	List<String> recipients = new ArrayList<>();
	candidates.forEach((key, value) -> {
	    recipients.add(value.getMember().getEmail());
	});
	recipients.add(getTaskMail(taskName));
	return recipients;
    }

    private String getTaskMail(String taskName) {
	if (isSupportTask(taskName)) {
	    return taskService.getByTaskName(Constants.FUNCTIONAL).getEmail();
	}
	return taskService.getByTaskName(Constants.RELEASER).getEmail();
    }

    private Map<String, Object> getArgs(
	    Map<String, CandidateViewDto> candidates) {
	Map<String, Object> args = new HashMap<>();
	candidates.forEach((key, value) -> {
	    args.put(key, value.getMember().buildFullName());
	});
	return args;
    }

    private Map<String, CandidateViewDto> getCandidates(String taskName) {
	Map<String, CandidateViewDto> args = new HashMap<>();
	if (taskName.equals("releaser")) {
	    CandidateViewDto releaser = candidateService
		    .findFirstByTaskNameAndStatusAndMemberShift(
			    Constants.RELEASER,
			    Status.CURRENT, null);
	    CandidateViewDto tester = candidateService
		    .findFirstByTaskNameAndStatusAndMemberShift(
			    Constants.TESTER,
			    Status.CURRENT, null);
	    args.put("releaser", releaser);
	    args.put("tester", tester);
	} else if (isSupportTask(taskName)) {
	    Arrays.asList(Shift.PAR, Shift.BGL)
		    .forEach(shift -> Arrays
			    .asList(Constants.FUNCTIONAL, Constants.TECHNICAL)
			    .forEach(task -> {
				final CandidateViewDto candidate = candidateService
					.findFirstByTaskNameAndStatusAndMemberShift(
						task, Status.CURRENT, shift);
				args.put(task + shift.name(), candidate);
			    }));
	} else {
	    throw new NotYetImplementedException(
		    "given task (" + taskName + ") is not yet handled...");
	}
	return args;
    }

    private boolean isSupportTask(String taskName) {
	return taskName.equals(Constants.SUPPORT);
    }
}
