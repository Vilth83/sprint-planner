package fr.vilth.sprintplanner.api.services;

import java.util.List;

import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.release.ReleaseCreateDto;
import fr.vilth.sprintplanner.domain.dtos.release.ReleaseViewDto;

public interface ReleaseService {

    List<ReleaseViewDto> findAllOrdered();

    ReleaseViewDto findLastRelease();

    EntityIdDto save(ReleaseCreateDto inputs);

    void incrementReleaseVersion();
}
