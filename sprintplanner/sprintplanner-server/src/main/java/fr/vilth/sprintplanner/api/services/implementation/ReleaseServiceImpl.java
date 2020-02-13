package fr.vilth.sprintplanner.api.services.implementation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import fr.vilth.sprintplanner.api.repositories.ReleaseRepository;
import fr.vilth.sprintplanner.api.services.ProjectService;
import fr.vilth.sprintplanner.api.services.ReleaseService;
import fr.vilth.sprintplanner.commons.api.AbstractService;
import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.release.ReleaseCreateDto;
import fr.vilth.sprintplanner.domain.dtos.release.ReleaseViewDto;
import fr.vilth.sprintplanner.domain.entities.Project;
import fr.vilth.sprintplanner.domain.entities.Release;

@Service
public class ReleaseServiceImpl extends AbstractService
	implements ReleaseService {

    private final ReleaseRepository releaseRepository;

    private final ProjectService projectService;

    public ReleaseServiceImpl(ReleaseRepository releaseRepository,
	    ProjectService projectService) {
	this.releaseRepository = releaseRepository;
	this.projectService = projectService;
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
    public void incrementReleaseVersion(Project project) {
	Release lastRelease = releaseRepository
		.findFirstByOrderByPiDescSprintDescWeekDesc();
	Release nextRelease = lastRelease.incrementRelease(project);
	ReleaseCreateDto newRelease = modelMapper.map(nextRelease,
		ReleaseCreateDto.class);
	save(newRelease);
    }
}
