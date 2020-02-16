package fr.vilth.sprintplanner.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.vilth.sprintplanner.api.services.ReleaseService;
import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.release.ReleaseCreateDto;
import fr.vilth.sprintplanner.domain.dtos.release.ReleaseViewDto;

@RestController
@RequestMapping("/releases")
public class ReleaseController {

    private final ReleaseService releaseService;

    public ReleaseController(ReleaseService releaseService) {
	this.releaseService = releaseService;
    }

    @GetMapping
    public List<ReleaseViewDto> findAll() {
	return releaseService.findAll();
    }

    @GetMapping("last")
    public ReleaseViewDto findLastRelease() {
	return releaseService.findLastRelease();
    }

    @PostMapping
    public EntityIdDto save(@Valid @RequestBody ReleaseCreateDto inputs) {
	return releaseService.save(inputs);
    }

    @PostMapping("/increment")
    public void incrementReleaseVersion() {
	releaseService.incrementReleaseVersion();
    }
}
