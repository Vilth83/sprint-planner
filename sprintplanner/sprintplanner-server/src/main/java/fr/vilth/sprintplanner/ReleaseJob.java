package fr.vilth.sprintplanner;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fr.vilth.sprintplanner.api.services.ReleaseService;
import fr.vilth.sprintplanner.commons.api.AbstractService;

@Service
public class ReleaseJob extends AbstractService {

    private final ReleaseService releaseService;

    public ReleaseJob(ReleaseService releaseService) {
	this.releaseService = releaseService;
    }

    @Scheduled(cron = "${cron.config.increment-version}")
    public void incrementReleaseVersion() {
	releaseService.incrementReleaseVersion();
    }
}
