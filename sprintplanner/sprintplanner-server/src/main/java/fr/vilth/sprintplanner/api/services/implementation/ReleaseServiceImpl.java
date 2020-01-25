package fr.vilth.sprintplanner.api.services.implementation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import fr.vilth.sprintplanner.api.repositories.ReleaseRepository;
import fr.vilth.sprintplanner.api.services.ReleaseService;
import fr.vilth.sprintplanner.commons.api.AbstractService;
import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.release.ReleaseCreateDto;
import fr.vilth.sprintplanner.domain.dtos.release.ReleaseViewDto;
import fr.vilth.sprintplanner.domain.entities.Release;

@Service
public class ReleaseServiceImpl extends AbstractService
	implements ReleaseService {

    private final ReleaseRepository releaseRepository;

    public ReleaseServiceImpl(ReleaseRepository releaseRepository) {
	this.releaseRepository = releaseRepository;
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
}
