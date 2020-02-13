package fr.vilth.sprintplanner.api.services;

import java.util.List;

import javax.validation.Valid;

import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.release.ReleaseCreateDto;
import fr.vilth.sprintplanner.domain.dtos.release.ReleaseViewDto;
import fr.vilth.sprintplanner.domain.entities.Project;

public interface ReleaseService {

    List<ReleaseViewDto> findAll();

    ReleaseViewDto findLastRelease();

    EntityIdDto save(@Valid ReleaseCreateDto inputs);

    void incrementReleaseVersion(Project project);
}
