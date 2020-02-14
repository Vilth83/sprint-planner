package fr.vilth.sprintplanner.api.services.implementation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import fr.vilth.sprintplanner.api.repositories.ReleaseRepository;
import fr.vilth.sprintplanner.api.services.CandidateService;
import fr.vilth.sprintplanner.api.services.ProjectService;
import fr.vilth.sprintplanner.api.services.ReleaseService;
import fr.vilth.sprintplanner.commons.api.AbstractService;
import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.candidate.CandidateViewDto;
import fr.vilth.sprintplanner.domain.dtos.project.ProjectViewDto;
import fr.vilth.sprintplanner.domain.dtos.release.ReleaseCreateDto;
import fr.vilth.sprintplanner.domain.dtos.release.ReleaseViewDto;
import fr.vilth.sprintplanner.domain.entities.Release;
import fr.vilth.sprintplanner.domain.types.Status;

@Service
public class ReleaseServiceImpl extends AbstractService
	implements ReleaseService {

    private final ReleaseRepository releaseRepository;

    private final ProjectService projectService;

    private final CandidateService candidateService;

    public ReleaseServiceImpl(ReleaseRepository releaseRepository,
	    ProjectService projectService, CandidateService candidateService) {
	this.releaseRepository = releaseRepository;
	this.projectService = projectService;
	this.candidateService = candidateService;
    }

    @Override
    public List<ReleaseViewDto> findAll() {
	List<Release> releases = releaseRepository.findAll();
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
	CandidateViewDto candidate = candidateService
		.findFirstByTaskNameAndStatus("releaser", Status.CURRENT);
	EntityIdDto assignee = convert(candidate, EntityIdDto.class);
	return new ReleaseCreateDto(pi, sprint, week, assignee);
    }
}
