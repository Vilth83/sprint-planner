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
import fr.vilth.sprintplanner.security.annotations.HasRoleUser;

/**
 * {@code RestController} to handle {@code Release}
 * 
 * @author Thierry VILLEPREUX
 */
@RestController
@RequestMapping("/releases")
public class ReleaseController {

    private final ReleaseService releaseService;

    /**
     * Protectec constructor that autowires needed beans.
     * 
     * @param releaseService the injected {@code ReleaseService}
     */
    public ReleaseController(ReleaseService releaseService) {
	this.releaseService = releaseService;
    }

    /**
     * Returns all releases ordered by version number descending.
     * 
     * @return a {@code List} of {@code ReleaseViewDto}
     */
    @GetMapping
    @HasRoleUser
    public List<ReleaseViewDto> findAllOrdered() {
	return releaseService.findAllOrdered();
    }

    /**
     * Return the last persisted {@code Release}
     * 
     * @return a {@code ReleaseViewDto}
     */
    @GetMapping("/last")
    public ReleaseViewDto findLastRelease() {
	return releaseService.findLastRelease();
    }

    /**
     * Persist given {@code ReleaseCreateDto} representing a {@code Release}
     * 
     * @param inputs the given {@code ReleaseCreateDto}
     * @return an {@code EntityIdDto} representing the id of the persisted
     *         {@code Release}
     */
    @PostMapping
    @HasRoleUser
    public EntityIdDto save(@Valid @RequestBody ReleaseCreateDto inputs) {
	return releaseService.save(inputs);
    }

    /**
     * Increments the {@code Release} version, persisting a new {@code Release}.
     */
    @PostMapping("/increment")
    public void incrementReleaseVersion() {
	releaseService.incrementReleaseVersion();
    }
}
