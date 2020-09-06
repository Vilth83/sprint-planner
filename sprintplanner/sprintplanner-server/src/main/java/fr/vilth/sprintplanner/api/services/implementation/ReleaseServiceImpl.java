package fr.vilth.sprintplanner.api.services.implementation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import fr.vilth.sprintplanner.api.repositories.ReleaseJpaRepository;
import fr.vilth.sprintplanner.api.services.CandidateService;
import fr.vilth.sprintplanner.api.services.ProjectService;
import fr.vilth.sprintplanner.api.services.ReleaseService;
import fr.vilth.sprintplanner.configuration.api.AbstractService;
import fr.vilth.sprintplanner.configuration.utils.Constants;
import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateNameDto;
import fr.vilth.sprintplanner.domain.dtos.project.ProjectViewDto;
import fr.vilth.sprintplanner.domain.dtos.release.ReleaseCreateDto;
import fr.vilth.sprintplanner.domain.dtos.release.ReleaseViewDto;
import fr.vilth.sprintplanner.domain.entities.Release;
import fr.vilth.sprintplanner.domain.types.Status;

/**
 * Implementation of {@code ReleaseService}
 * 
 * @author Thierry VILLEPREUX
 */
@Service
public class ReleaseServiceImpl extends AbstractService
	implements ReleaseService {

    private final ReleaseJpaRepository releaseRepository;

    private final ProjectService projectService;

    private final CandidateService candidateService;

    protected ReleaseServiceImpl(ReleaseJpaRepository releaseRepository,
	    ProjectService projectService, CandidateService candidateService) {
	this.releaseRepository = releaseRepository;
	this.projectService = projectService;
	this.candidateService = candidateService;
    }

    @Override
    public List<ReleaseViewDto> findAllOrdered() {
	List<Release> releases = releaseRepository
		.findAllByOrderByPiDescSprintDescWeekDesc();
	return convertList(releases, ReleaseViewDto.class);
    }

    @Override
    public ReleaseViewDto findLastRelease() {
	Release lastRelease = releaseRepository
		.findFirstByOrderByPiDescSprintDescWeekDesc();
	return convert(lastRelease, ReleaseViewDto.class);
    }

    @Override
    public EntityIdDto save(@Valid ReleaseCreateDto inputs) {
	Release release = convert(inputs, Release.class);
	Release saved = releaseRepository.save(release);
	return convert(saved, EntityIdDto.class);
    }

    @Override
    public void incrementReleaseVersion() {
	ReleaseViewDto lastRelease = findLastRelease();
	ReleaseCreateDto nextRelease = incrementRelease(lastRelease);
	save(nextRelease);
    }

    private ReleaseCreateDto incrementRelease(ReleaseViewDto lastRelease) {
	ProjectViewDto project = projectService.getProject();
	int pi = lastRelease.getPi();
	int week = lastRelease.getWeek();
	int sprint = lastRelease.getSprint();
	week++;
	if (week > project.getSprintDuration()) {
	    week = 1;
	    sprint++;
	    if (sprint > project.getPiDuration()) {
		sprint = 1;
		pi++;
	    }
	}
	CandidateNameDto candidate = candidateService
		.findCandidateFullNameByTaskAndStatus(
			Constants.RELEASER, Status.CURRENT);
	String candidateName = candidate.getFirstname() + " "
		+ candidate.getLastname();
	return new ReleaseCreateDto(pi, sprint, week, candidateName);
    }
}
